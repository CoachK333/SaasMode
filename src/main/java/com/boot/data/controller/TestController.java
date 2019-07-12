package com.boot.data.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.boot.data.dao.DutyRepository;
import com.boot.data.dao.UserRepository;
import com.boot.data.dto.Result;
import com.boot.data.entity.Duty;
import com.boot.data.entity.Product;
import com.boot.data.entity.Production;
import com.boot.data.entity.User;
import com.boot.data.service.ProductService;
import com.boot.data.util.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author 98548
 * @create 2019-02-19 15:06
 * @description
 */
@RestController
@RequestMapping("/test")
@Api("/测试接口")
@Slf4j
public class TestController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DutyRepository dutyRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private TestService testService;

    @Value("${path123}")
    private String path123;
    //    @Value("${chakanyinyong}")
//    private String chakanyinyong;
    @Value("${file.path}")
    private String filePath;
    @Value("${zdurl.auth}")
    private String zdAuth;
    @Value("${zdurl.auth.headers.Referer}")
    private String zdAuthHeaderRefer;

    @PostMapping("/add")
    public String addOne(String code, String name) {
        User user = new User();
        user.setName(name);
        user.setDesc(code);
        userRepository.save(user);
        return "ok";
    }

    @GetMapping("/getAll")
    public String getAll() {
        List<User> users = userRepository.findAll();
        return JSON.toJSONString(users);
    }

    @GetMapping("/test001")
    public String test001() {
        return JSON.toJSONString(userRepository.getAll());
    }

    @GetMapping("/test002")
    public String test002(String name) {
        Integer exo = userRepository.exo(name);
        return JSON.toJSONString(exo);
    }

    @PostMapping("/zbDetails")
    @ApiOperation("值班情况展示--按照(周/组别)展示值班情况")
    public String zbDetails(@ApiParam("年和月份（yyyyMMdd）开始") @RequestParam String start,
                            @ApiParam("年和月份（yyyyMMdd）结束") @RequestParam String end,
                            @ApiParam("类型ID 可不传（所有类型）") @RequestParam(required = false, defaultValue = "") String type) throws ParseException {
        if (org.apache.commons.lang3.StringUtils.isAnyBlank(start, end)) {
            return new String("参数为空!");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date st, en;

        st = sdf.parse(start);
        en = sdf.parse(end);

        return sdf.format(st) + sdf.format(en);
    }

    @GetMapping("/test003")
    public List<Duty> test003(String start, String end) throws ParseException {

        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyyMMdd"));

        LocalDateTime startTime = LocalDateTime.of(startDate, LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(endDate, LocalTime.MAX);

        List<Duty> duties = dutyRepository.getDutiesByTimeZone(DateUtils.localDateTime2Date(startTime), DateUtils.localDateTime2Date(endTime));
        Map<LocalDate, List<Duty>> map = new HashMap<>();
        for (Duty duty : duties) {
            Date dutyStartDate0 = duty.getDutyStartDate();
            LocalDate localDate = DateUtils.date2LocalDate(dutyStartDate0);
            if (!map.containsKey(localDate)) {
                List<Duty> dutyList = new ArrayList<>();
                dutyList.add(duty);
                map.put(localDate, dutyList);
            } else {
                List<Duty> dutyList = map.get(localDate);
                dutyList.add(duty);
            }
        }
        return duties;
    }


    @PutMapping("/test004")
    public String test004(Long id) {
        User one = userRepository.getOne(id);
        one.setDesc("hahah");

        User user = new User();
        user.setName("zhangxiaosan");
        user.setCreateDate(DateUtils.localDateTime2Date(LocalDateTime.of(LocalDate.now(), LocalTime.MAX)));

        List<User> users = new ArrayList<>();
        users.add(one);
        users.add(user);
        userRepository.saveAll(users);
        return "ok";
    }

    @GetMapping("/test005")
    public String tests005() {
        User user = new User();
        user.setName("第五人格");
        user.setComment("<html>哈哈哈</html>");
        userRepository.save(user);
        return "ok";
    }

    @PostMapping("/test006")
    public String test006(String id) {
        if (StringUtils.isBlank(id)) {
            return "参数为空!";
        }
        Production production = productService.getOne(id);
        String version = production.getVersion();
        return JSON.toJSONString(production);
    }

    @PostMapping(value = "/test007")
    public String test007(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "param is null!";
        }
        for (Object o : list) {
            System.out.println(o.getClass() + " : " + o.toString());
        }
        return "ok";
    }

    @PostMapping(value = "/test008")
    public String test008(String id) {
        String[] strings = id.split(",");
        System.out.println(JSON.toJSON(strings));
        return "ok";
    }

    @PostMapping(value = "/test009")
    public String test009(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        System.out.println(files.size());
        return "ok";
    }

    @GetMapping(value = "/test010")
    public String test010(Long id) {
        System.out.println(id + path123);
        return "ok";
    }

    @GetMapping(value = "/test011")
    public String test011(HttpServletRequest request, Long id, String dateStr) {
        String code = request.getHeader("code");
        LocalDate parse = null;
        try {
            parse = LocalDate.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return "日期格式有误!";
        }
        return code + " : " + String.valueOf(id) + "<" + parse.toString() + ">";
    }

    @GetMapping(value = "/test012")
    public String test012() {
//        return filePath + chakanyinyong;
        return filePath;
    }

    @PostMapping(value = "/test013")
    public String test013(String token) {

        //请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Refer", zdAuthHeaderRefer);
        //请求参数
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("token", token);
        //请求体
        HttpEntity httpEntity = new HttpEntity(params, headers);
//        zdAuth.indexOf()
        //url
        String domainUrl = zdAuth.replace("domainUrl", "http://dev.zdha.cn");
        domainUrl = domainUrl + "?token=" + token;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(domainUrl, HttpMethod.GET, httpEntity, String.class);
        String body = responseEntity.getBody();

        System.out.println("domainURL:  " + domainUrl);
        JSONObject jsonObject = JSONUtil.parseObj(body);

        return jsonObject.toString();
    }

    @GetMapping(value = "/test015")
    public Result test014() {

        Page<Production> productionPage = productService.getAll();
        Result result = new Result();
        result.setResult(productionPage);
        return result;
    }

    @GetMapping(value = "/test014")
    public Result test015() {
        List<Product> list = testService.test();
        Result result = new Result();
        result.setResult(list);
        return result;
    }

    @GetMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {

        File file = new File("C:\\Users\\98548\\Desktop\\myfile.xls");

        response.setContentType("application/force-download");
//        response.setContentType("application/octet-stream");
//        response.setContentType("application/vnd.ms-excel");

//        response.addHeader("Content-Disposition", "attachment;fileName=" + "排班审批模板.xls");
        String fileName = new String("排班审批模板.xls".getBytes("UTF-8"), "ISO8859-1");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.addHeader("content-length", String.valueOf(file.length()));
        byte[] buffer = new byte[10240];
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file)); OutputStream os = response.getOutputStream()) {
            int i;
            while ((i = bis.read(buffer)) != -1) {
                os.write(buffer, 0, i);
            }
        } catch (IOException e) {
            log.error("文件下载失败:{}", e);
            throw new Exception("文件下载失败!");
        }
        return;
    }

    @GetMapping("/test111")
    public List test111(String s) {
        List list = testService.test111(s);
        return list;
    }
}