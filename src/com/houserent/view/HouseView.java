package com.houserent.view;

import com.houserent.domain.House;
import com.houserent.service.Houseservice;
import com.houserent.utils.Utility;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.rmi.CORBA.Util;
import java.util.Scanner;

public class HouseView {
    private boolean loop =true;
    private char key = ' ';
    private Houseservice houseservice = new Houseservice(3);
    public void listHouses()
    {
        System.out.println("----------------房屋列表-----------------");
        System.out.println("编号\t房主\t\t电话\t\t住址\t\t月租\t\t状态");
       House[] houses = houseservice.list();
       for(int i =0 ;i< houses.length;i++)
       {
           if (houses[i] == null)
           {
               break;
           }
           System.out.println(houses[i]);
       }
        System.out.println("----------------打印完毕----------------");
    }
    public void mainMenu()
    {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("--------房屋出租系统------------");
            System.out.println("-----------1.新增房源----------");
            System.out.println("-----------2.查找房源----------");
            System.out.println("-----------3.删除房源----------");
            System.out.println("-----------4.修改房屋信息----------");
            System.out.println("-----------5.房屋列表-------------");
            System.out.println("-----------6.退出----------------");
            System.out.println("请选择：");
            key = Utility.readChar();
            switch (key)
            {
                case '1':
                    addhouse();
                    break;
                case '2':
                    searchhouse();
                    break;
                case '3':
                    delhouse();
                    break;
                case '4':
                    modifyhouse();
                    break;
                case '5':
                    listHouses();
                    break;
                case '6':
                    exithouse();
                    break;
            }


        }while(loop);

    }
    public void modifyhouse()
    {
        boolean loop = true;
        do {
            System.out.println("请输入要修改的编号(-1退出):");
            int nid = Utility.readInt();
            if(nid==-1)
            {
                loop =false;
                System.out.println("退出成功");
                break;
            }
            System.out.println("请选择要修改的信息：");
            System.out.println(" 1.姓名      2.电话   ");
            System.out.println(" 3.地址      4.租金   ");
            System.out.println(" 5.状态      -1.退出   ");
            int choice = Utility.readInt();
            if(choice==-1)
            {
                loop = false;
                System.out.println("退出成功");
                break;
            }
            if(houseservice.modify(nid,choice))
            {
                System.out.println("修改成功");
            }
            else
            {
                System.out.println("修改失败");
            }
        }while (loop);

    }
    public void searchhouse()
    {
        System.out.println("-------------查找房屋信息-----------");
        System.out.println("请输入要查找的编号");
        int s_id=Utility.readInt();
        if(houseservice.search(s_id))
        {
            System.out.println("----------查找成功-------------");
            return;
        }
        else{
            System.out.println("----------查找失败--------------");
            return;
        }
    }
    public void exithouse()
    {
        char choice = Utility.readConfirmSelection();
        if(choice == 'Y')
        {
            System.out.println("退出成功");
            loop =false;
            return;
        }else
        {
            System.out.println("取消退出");
            return;
        }
    }
    public void addhouse()
    {
        System.out.println("----------添加房屋-------------");
        System.out.println("请输入姓名");
        String name = Utility.readString(8);
        System.out.println("请输入电话");
        String phone = Utility.readString(12);
        System.out.println("请输入地址");
        String address = Utility.readString(10);
        System.out.println("请输入月租金");
        int rent = Utility.readInt();
        System.out.println("请输入当前状态");
        String state = Utility.readString(10);
        House newhouse = new House(0, name, phone, address, rent, state);
        if(houseservice.add(newhouse))
        {
            System.out.println("------------添加成功--------------");
            return;
        }
        else
        {
            System.out.println("-------------添加失败--------------");
            return;
        }
    }
    public void delhouse()
    {
        System.out.println("-------------删除房屋------------------");
        System.out.println("请选择待删除房屋编号(-1.退出)：");
        int delid = Utility.readInt();
        if(delid==-1)
        {
            System.out.println("-------------取消删除------------");
            return;
        }
        char choice = Utility.readConfirmSelection();
        if(choice =='Y')
        {
            if(houseservice.del(delid))
            {
                System.out.println("------------删除成功------------");
                return;
            }
            else
            {
                System.out.println("-------------删除失败------------");
                return;
            }


        }
        else
        {
            System.out.println("-------------取消删除--------------");
            return;
        }

    }

}
