package com.boot.data.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.boot.data.dao.UserRepository;
import com.boot.data.dto.UserDto;
import com.boot.data.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.omg.CosNaming.IstringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * @author 98548
 * @create 2018-08-20 16:23
 * @description
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestService testService;

    int count;

    @RequestMapping(value = "/001")
    public String test001() {
        count++;
        String s = String.valueOf(count);

        System.out.println(s);
        return s;
    }

    @RequestMapping(value = "/test002", method = RequestMethod.GET)
    public String test002(String name) {
        List<UserDto> dtos = userRepository.getByName(name);
        return JSON.toJSONString(dtos);
    }

    @PostMapping("/test003")
    public String test003(HttpServletRequest request) {
        String p1 = request.getParameter("p1");
        return p1;
    }

    @PostMapping("/test004")
    public String test004(String p1, int p2) {
        return p1;
    }

    @RequestMapping("/test005")
    public String test005(String s1) {

        redisTemplate.opsForValue().set("test001", s1);
        return redisTemplate.opsForValue().get("test001");
    }

    @RequestMapping("/test006")
    public String test006() {
        return "234y2iu3hjsndf";
    }

    @RequestMapping("/test007")
    public String download(HttpServletResponse response) throws IOException {
        // 下载本地文件
        String fileName = "target.xls"; // 文件的默认保存名
        // 读到流中
        FileInputStream inputStream = new FileInputStream("temp.xls");

        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "deal ok";
    }


    @RequestMapping("/test008")
    public String test008() throws IOException {
        List<Map<String, String>> maps = userRepository.getByName();
        return JSON.toJSONString(maps);
    }

    @GetMapping("/getIP")
    public String getIPAddr(HttpServletRequest request) throws SocketException {
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP
        Enumeration<NetworkInterface> netInterfaces;
        netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        boolean finded = false;// 是否找到外网IP
        while (netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            System.out.println(JSON.toJSONString(address));
            while (address.hasMoreElements()) {
                ip = address.nextElement();
                if (!ip.isSiteLocalAddress()
                        && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
                    netip = ip.getHostAddress();
                    finded = true;
                    break;
                } else if (ip.isSiteLocalAddress()
                        && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
                    localip = ip.getHostAddress();
                }
            }
        }
        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }
    }

    @RequestMapping("/test009")
    public String getPath() {
        return FileUtils.getPath002();
    }

    @RequestMapping("/test010")
    public String test010(String name) {

        User user = new User();
        user.setId("002");
        user.setName("坏事高一点");

        userRepository.save(user);

        List<User> users = userRepository.findAll(new Specification<User>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                predicates.add(criteriaBuilder.equal(root.get("name"), name));

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
        System.out.println(users);

        return "ok" + name;
    }

    @RequestMapping("/test011")
    public String test011(Integer integer) {
        return integer.toString();
    }

    @RequestMapping("/test012")
    public String test012(MultipartFile file, String code) {
        String result = testService.upload(file);
        return result + code;
    }
}
