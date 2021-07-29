package com.tml.web.controller;

import com.sun.deploy.net.HttpResponse;
import com.tml.exception.DeleteException;
import com.tml.exception.InsertException;
import com.tml.exception.UpdateException;
import com.tml.pojo.Activity;
import com.tml.pojo.msg.Result;
import com.tml.service.ActivityService;
import com.tml.utils.ControllerUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("act")
public class ActivityController {

    @Resource
    private ActivityService activityService;

    @GetMapping("list")
    public List<Activity> list(@RequestParam Map<String,Object> map){
        return activityService.getBySome(map);
    }
    @GetMapping("get/{id}")
    public Map<String, Object> get(@PathVariable String id){
        return activityService.get(id);
    }

    @PostMapping
    public Map<String,Object> add(@RequestParam Map<String, Object> activity ,HttpSession session){
        ControllerUtil.initInsert(activity,session);
        int insert = activityService.insert(activity);
        if(insert<1){
            throw new InsertException("添加失败,请检查数据的正确性!");
        }
        return Result.MSG;
    }

    @DeleteMapping("{ids}")
    public Map<String,Object> delete(@PathVariable Serializable... ids){
        int delete = activityService.delete(ids);
        if(delete<1){
            throw new DeleteException("删除失败!!");
        }
        return Result.MSG;
    }

    @PostMapping("update.do")
    public Map<String,Object> update(@RequestParam Map<String, Object> activity,HttpSession session){
        ControllerUtil.initUpdate(activity,session);
        int update = activityService.update(activity);
        if(update<1){
            throw new UpdateException("更新数据失败!!请检查数据的正确性!");
        }
        return Result.MSG;
    }

    /*导出数据生成excel并下载*/
    @GetMapping("export.do")
    public void download(HttpServletResponse response,@RequestParam Map<String,Object> map){
        /*创建空的excel文件*/
        HSSFWorkbook sheets = new HSSFWorkbook();
        /*创建页签*/
        HSSFSheet sheet = sheets.createSheet();
        /*创建行*/
        int r=0;
        HSSFRow row = sheet.createRow(r++);
        /*创建单元格*/
        int c=0;
        row.createCell(c++).setCellValue("名称");
        row.createCell(c++).setCellValue("所有者");
        row.createCell(c++).setCellValue("开始日期");
        row.createCell(c++).setCellValue("结束日期");

        List<Activity> activities = activityService.getBySome(map);
        for (Activity activity : activities) {
            row = sheet.createRow(r++);
            c=0;
            row.createCell(c++).setCellValue(activity.getName());
            row.createCell(c++).setCellValue(activity.getOwner());
            row.createCell(c++).setCellValue(activity.getStartDate());
            row.createCell(c++).setCellValue(activity.getEndDate());
        }
        response.addHeader("Content-Disposition", "attachment;filename=activity.xls");
        try {
            sheets.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*将excel的数据导入到数据库*/
    @PostMapping("import.do")
    public Map<String,Object> importExcel(MultipartFile file,HttpSession session){
        /*解析Excel*/
        /*创建Excel*/
        try {
            HSSFWorkbook sheets = new HSSFWorkbook(file.getInputStream());
            /*获取页签*/
            HSSFSheet sheet = sheets.getSheetAt(0);
            /*获取数据:从第二行开始,第一行是表头*/
            int r=1;
            HSSFRow row;
            List<Activity> list = new ArrayList<>();
            while ((row=sheet.getRow(r++))!=null){
                int c=0;
                Activity activity = new Activity();
                activity.setName(row.getCell(c++).getStringCellValue());
                activity.setOwner(row.getCell(c++).getStringCellValue());
                activity.setStartDate(row.getCell(c++).getStringCellValue());
                activity.setEndDate(row.getCell(c++).getStringCellValue());
                ControllerUtil.initInsert(activity,session);
                list.add(activity);
            }
            activityService.insertList(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.MSG;
    }
}
