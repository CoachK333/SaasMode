package com.boot.data.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.boot.data.dao.UserRepository;
import com.boot.data.dto.Result;
import com.boot.data.entity.Product;
import com.boot.data.entity.Production;
import com.boot.data.entity.User;
import com.boot.data.service.ProductService;
import com.boot.data.util.DateUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @PostMapping("/test003")
    public String test003(MultipartFile file) {
        List<VOExcelData> list = new ArrayList();

        try (InputStream inputStream = file.getInputStream()) {

            Workbook workbook = new HSSFWorkbook(inputStream);
            Sheet sheet001 = workbook.getSheetAt(0);
            int i0 = sheet001.getFirstRowNum();
            int iend = sheet001.getLastRowNum();

            System.out.println(i0);
            System.out.println(iend);

            Row row001 = sheet001.getRow(i0);
            short j0 = row001.getFirstCellNum();
            short jend = row001.getLastCellNum();
            System.out.println(j0);
            System.out.println(jend);

            String date_prefix = row001.getCell(0).getStringCellValue();
            String dayStr = row001.getCell(1).getStringCellValue();
            String yearStr = date_prefix.substring(0, 4);
            String monthStr = date_prefix.substring(4, 6);

            LocalDate startDate;


            Set<String> dutyTypes = new HashSet<>();
            for (int i = i0 + 2; i <= iend; i++) {
                Row row = sheet001.getRow(i);
                startDate = LocalDate.of(Integer.parseInt(yearStr), Integer.parseInt(monthStr), Integer.parseInt(dayStr));
                for (short j = (short) (j0 + 1); j < jend; j++) {
                    VOExcelData voExcelData = new VOExcelData();

                    voExcelData.setDate(DateUtils.localdateToDate(startDate));
                    voExcelData.setName(row.getCell(j0).getStringCellValue());
                    String content = row.getCell(j).getStringCellValue();
                    voExcelData.setContent(content);
                    dutyTypes.add(content);
                    list.add(voExcelData);
                    startDate = startDate.plusDays(1);
                }
            }
            System.out.println(JSON.toJSONString(list));
            System.out.println(dutyTypes);
            for (VOExcelData voExcelData : list) {
                String content = voExcelData.getContent();


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(list);
    }

    @GetMapping("/test004")
    public String test004(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userRepository.list(pageable);
        System.out.println(users.getTotalElements());
        System.out.println(users.getNumberOfElements());
        System.out.println(users.getNumber());
        System.out.println(users.getTotalPages());
        System.out.println(users.getSize());
        return JSON.toJSONString(users.getContent());
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