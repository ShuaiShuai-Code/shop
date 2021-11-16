package com.fenboshi.fboshi.bean;


import java.util.List;

public class GooldsTypeBean {
     private List<GooldsTypeBean> list;
     private String id;
     private String name;
     private int selected;//1

     public int getSelected() {
          return selected;
     }

     public void setSelected(int selected) {
          this.selected = selected;
     }

     public List<GooldsTypeBean> getList() {
          return list;
     }

     public void setList(List<GooldsTypeBean> list) {
          this.list = list;
     }

     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }
}