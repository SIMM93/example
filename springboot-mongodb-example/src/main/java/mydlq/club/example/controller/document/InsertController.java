package mydlq.club.example.controller.document;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mydlq.club.example.service.document.InsertService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author mydlq
 */
@Api(tags = "文档操作-插入文档")
@RestController
@RequestMapping("/document")
public class InsertController {

    @Resource
    private InsertService insertService;

    @ApiOperation(value = "插入【一条】文档数据，如果文档信息已经【存在就抛出异常】",
            notes = "插入【一条】文档数据，如果文档信息已经【存在就抛出异常】。")
    @PostMapping("/insert/one")
    public Object insertData() {
        return insertService.insert();
    }

    @PostMapping("/insert/oneJson")
    public Object insertDataJson(@RequestBody String newMssage) {
        insertService.insertJson(newMssage);
        return "{   \n" +
                "    \"code\": \"SUCCESS\",\n" +
                "    \"message\": \"成功\"\n" +
                "}";
    }

    @ApiOperation(value = "插入【多条】文档数据，如果文档信息已经【存在就抛出异常】",
            notes = "插入【多条】文档数据，如果文档信息已经【存在就抛出异常】。")
    @PostMapping("/insert/many")
    public Object insertManyData() {
        return insertService.insertMany();
    }

}
