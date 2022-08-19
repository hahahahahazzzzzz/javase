package com.houserent.service;

import com.houserent.domain.House;
import com.houserent.utils.Utility;

public class Houseservice {
    private int houseNums = 0;
    private House[] houses;
    private int id_count = 0;

    public Houseservice(int size) {
        houses = new House[size];
    }

    public House[] list() {
        return houses;
    }

    public boolean add(House newhouse) {
        if (houseNums == houses.length) {
            House[] house1 = new House[houses.length + 1];
            for (int i = 0; i < houses.length; i++) {
                house1[i] = houses[i];
            }
            houses = house1;
            house1 = null;
        }
        houses[houseNums] = newhouse;
        houseNums++;
        id_count++;
        newhouse.setId(id_count);
        return true;
    }


    public boolean del(int delid) {
        if (delid > houses[houseNums-1].getId()) {
            System.out.println("无此房屋编号");
            return false;
        }
        for (int i = delid - 1; i < houseNums-1; i++) {
            houses[i] = houses[i + 1];
        }
        houses[houseNums-1] = null;
        houseNums--;
        for (int i = delid - 1; i <=houseNums-1; i++) {
            int a = houses[i].getId();
            houses[i].setId(a - 1);
        }
        id_count=houses[houseNums-1].getId();
        return true;
    }

    public boolean search(int s_id) {
        System.out.println("你要查找的id：");
        int r_id = -1;
        for (int i = 0; i < houseNums; i++) {
            if (houses[i].getId() == s_id) {
                r_id = i;
            }
        }
        if (r_id == -1) {
            System.out.println("暂无此编号");
            return false;
        } else {
            System.out.println(houses[r_id]);
        }
        return true;
    }

    public boolean modify(int nid, int choice) {
        if(nid>houseNums)
        {
            System.out.println("暂未此编号");
            return false;
        }
        switch (choice) {
            case 1:
                System.out.println("修改前的姓名"+houses[nid-1].getName());
                System.out.println("请输入修改后的姓名：");
                String newname = Utility.readString(8);
                houses[nid-1].setName(newname);
                System.out.println(houses[nid-1]);
                return true;
            case 2:
                System.out.println("修改前的电话"+houses[nid-1].getPhone());
                System.out.println("请输入修改后的电话：");
                String newphone = Utility.readString(12);
                houses[nid-1].setPhone(newphone);
                System.out.println(houses[nid-1]);
                return true;
            case 3:
                System.out.println("修改前的地址"+houses[nid-1].getAddress());
                System.out.println("请输入修改后的地址：");
                String newadress = Utility.readString(10);
                houses[nid-1].setAddress(newadress);
                System.out.println(houses[nid-1]);
                return true;
            case 4:
                System.out.println("修改前的租金"+houses[nid-1].getRent());
                System.out.println("请输入修改后的租金：");
                int newarent = Utility.readInt();
                houses[nid-1].setRent(newarent);
                System.out.println(houses[nid-1]);
                return true;
            case 5:
                System.out.println("修改前的状态"+houses[nid-1].getState());
                  System.out.println("请输入修改后的状态：");
                String newstate = Utility.readString(10);
                houses[nid-1].setState(newstate);
                System.out.println(houses[nid-1]);
                return true;
            default:
                System.out.println("输入的选项有误，请重新选择");
                return false;
        }
    }
}
