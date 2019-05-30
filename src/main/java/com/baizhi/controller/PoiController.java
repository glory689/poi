package com.baizhi.controller;

import com.baizhi.entity.Student;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/poi")
public class PoiController {
    /* @Autowired
     private PoiService poiService;*/
    @RequestMapping("poiOut")
    public void poiOut() throws IOException {
        //相当于从数据库中查询到的List<Student>
        List<Student> list=new ArrayList<>();
        Student student1 = new Student(1, "yy", "000", new Date());
        Student student2 = new Student(2, "pp", "000", new Date());
        Student student3 = new Student(3, "ff", "000", new Date());
        Student student4 = new Student(4, "ll", "000", new Date());
        Student student5 = new Student(5, "nn", "000", new Date());

        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        list.add(student5);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("student");
        //标题行
        HSSFRow row = sheet.createRow(0);
        String[] titles={"编号","姓名","密码","生日"};

        for (int i=0;i<titles.length;i++){
            String title=titles[i];
            HSSFCell cell=row.createCell(i);
            cell.setCellValue(title);
        }
        for (int i=0;i<list.size();i++){
            HSSFRow row1=sheet.createRow(i+1);
            row1.createCell(0).setCellValue(list.get(i).getId());
            row1.createCell(1).setCellValue(list.get(i).getName());
            row1.createCell(2).setCellValue(list.get(i).getPassword());
            HSSFCell cell=row1.createCell(3);
            cell.setCellValue(list.get(i).getBir());
        }
        workbook.write(new File("D:/student.xls"));
    }

    @RequestMapping("poiIn")
    public void poiIn(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheet("学生");
        int lastRowNum = sheet.getLastRowNum();
        for (int i=0;i<lastRowNum;i++){
            int id=(int)sheet.getRow(i).getCell(0).getNumericCellValue();
            String name = sheet.getRow(i).getCell(1).getStringCellValue();
            String password = sheet.getRow(i).getCell(2).getStringCellValue();
            Date bir = sheet.getRow(i).getCell(3).getDateCellValue();
            Student student = new Student(id, name, password, bir);
            System.out.println(student);
        }
    }
}


