package com.exam.sbb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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




}
