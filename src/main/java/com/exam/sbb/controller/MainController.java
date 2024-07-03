package com.exam.sbb.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

 private List<Aticle> aticles = new ArrayList<>(
         Arrays.asList(
                 new Aticle("제목","내용"),
                 new Aticle("제목","내용"),
                 new Aticle("제목","내용")));


  @GetMapping("/addAticle")
  @ResponseBody
  public String addAticle(String title,String body){


    Aticle aticle = new Aticle(title,body);
    aticles.add(aticle);

    return "%d번 게시물이 생성되었습니다".formatted(aticle.getId());


  }

  @GetMapping("/aticle/{id}")
  @ResponseBody
  public Aticle getArticle(@PathVariable int id){

     Aticle aticle = aticles
               .stream()
             .filter(a->a.getId() == id)
               .findFirst().orElse(null);

      return aticle;
  }

  @GetMapping("/modifyAticle/{id}")
  @ResponseBody
  public String modifyAticle(@PathVariable int id, String title  ,String body) {

    Aticle aticle = aticles
            .stream()
            .filter(a -> a.getId() == id)
            .findFirst().orElse(null);

    if (aticle == null) {
      return "%d번 게시물은 존재하진 않습니다".formatted(id);
    }
    aticle.setTitle(title);
    aticle.setBody(body);
    return "%d번게시물이 수정되었습니다".formatted(aticle.getId());

  }

  @GetMapping("/deleteAticle/{id}")
  @ResponseBody
  public String deleteAticle(@PathVariable int id) {

    Aticle aticle = aticles
            .stream()
            .filter(a -> a.getId() == id)
            .findFirst().orElse(null);

    if (aticle == null) {
      return "%d번 게시물은 존재하진 않습니다".formatted(id);
    }
    aticles.remove(aticle);
    return "%d번 게시물이 삭제되었습니다".formatted(aticle.getId());

  }
  @GetMapping("/addPersonOnlyWay")
  @ResponseBody
  public Aticle.Person addPersonOnlyWay(int id, int age, String name){
    Aticle.Person p =new Aticle.Person(id,age,name);
    return p;

  }

  @GetMapping("/addPerson/{id}")
  @ResponseBody
  public Aticle.Person addPerson(Aticle.Person p){

    return p;

  }



 //class 추가
  @Getter
  @Setter
  @AllArgsConstructor
  class Aticle{
    private static int lastId= 0;

    private int id;
    private String title;
    private String body;

    public Aticle(String title, String body){
      this(++lastId, title, body);
    }

   @AllArgsConstructor
   @NoArgsConstructor
   @Setter
   @Getter
   static class Person{
     public Person(int age, String name) {
       this.age = age;
       this.name = name;
     }

     private int id;
      private int age;
      private String name;
    }


 }









}
