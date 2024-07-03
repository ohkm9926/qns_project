package com.exam.sbb.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@Controller
public class MainController {

   private int increaseNo = -1;
  @RequestMapping("/sbb")
  //아래 함수의 리턴값을 그래도 브라우저에 표시한다
  //아래 함수의 리턴값을 문자열화 해서 브라우저 응답을 바디에 담는다.
  @ResponseBody
    public  String index(){
      return  "안녕하세요12";
  }

  @GetMapping("/test")
  @ResponseBody
  public String showMain(){
    return """
            <h1>안녕하세요<h1/>
            <input type ="text" placeholder="입력해주세요."/>
            """;
  }
  @GetMapping("/get")
  @ResponseBody
  public String showGet(){
    return """
            <form method ="POST" action ="/page2">
            <input type ="number" name ="age" placeholder = "나이입력" />
            <input type ="submit" value ="page2로 Post방식으로 이동" />
            </form>
            """;
  }

  @PostMapping ("/page2")
  @ResponseBody
  public String showPost(@RequestParam(defaultValue = "0") int age){
    return """
            <h1>입력나이 : %d</h1>
            <h1>여기는 post방식 페이지입니다.<h1/>
            
            """.formatted(age);
  }

  @GetMapping ("/page1")
  @ResponseBody
  public String showPost1(@RequestParam(defaultValue = "0") int age){
    return """
            <h1>입력나이 : %d</h1>
            <h1>여기는 get방식 페이지입니다.<h1/>
            
            """.formatted(age);
  }

  @GetMapping ("/plus")
  @ResponseBody
  public int plus(int a , int b){
    return a+b;
  }

  @GetMapping ("/mius")
  @ResponseBody
  public int mius(int a , int b){
    return a-b;
  }

  @GetMapping ("/increase")
  @ResponseBody
  public int increase(){
    increaseNo++;
    return increaseNo;

  }
  @GetMapping ("/gugudan")
  @ResponseBody
  public String gugudan(int dan ,int limit){
    String rs ="";
    for(int i = 1; i<=limit; i++){

      rs += "%d * %d =%d<br>\n".formatted(dan,i,dan*i);

    }

    return rs;

  }

  @GetMapping("/mbti/{name}")
  @ResponseBody
  public String  showMbti(@PathVariable String name){
       return switch (name){
         case "홍길동" -> "INFP";
         case "홍길순" -> "ENFP ";
         case "임꺽정" -> "ESFJ";
         case "박상원" -> "INFJ";
         default -> "모름";
       };


  }
  @GetMapping("/saveSession/{name}/{value}")
  @ResponseBody
  public String saveSession(@PathVariable String name , @PathVariable String value, HttpServletRequest req){
    HttpSession session = req.getSession();
    session.setAttribute(name ,value);

    return "세션변수 %s의 값이 %s로 설정되었습니다".formatted(name,value);
  }

  @GetMapping("/getSession/{name}")
  @ResponseBody
  public String getSession(@PathVariable String name ,HttpSession session){
   //req =>쿠키=> JSESSIONID => 세션을 얻을수있다.

    String value = (String) session.getAttribute(name);

    return "세션변수 %s의 값이 %s 입니다".formatted(name,value);
  }

  @GetMapping("/addAticle")
  @ResponseBody
  public String addAticle(String title,String body){
    int id = 1;

    Aticle aticle = new Aticle(id,title,body);

    return "%d번 게시물이 생성되었습니다".formatted(id);


  }
  @AllArgsConstructor
  class Aticle{
    private int id;
    private String title;
    private String body;

  }






}
