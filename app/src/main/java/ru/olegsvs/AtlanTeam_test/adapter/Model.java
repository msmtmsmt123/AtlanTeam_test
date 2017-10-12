package ru.olegsvs.AtlanTeam_test.adapter;


import java.util.List;

import ru.olegsvs.AtlanTeam_test.model.User;

/**
 * Created by oleg.svs on 11.10.2017.
 */
 
public class Model {

    //types
    public static final int POST=0;
    public static final int COMMENT=1;
    public static final int USER=2;
    public static final int PHOTO=3;
    public static final int TODO=4;

    public int type;

    public String url;
    public String title;
    public String desc;

    //карточка с 5ью пользователями
    public String name1;
    public String name2;
    public String name3;
    public String name4;
    public String name5;


    //Конструктор для изображения с описанием
    public Model(int type, String title, String url)
    {
        this.type=type;
        this.url=url;
        this.title=title;

    }

    //Конструктор для пары Заголовок<->Описание(comment , post, todo)
    public Model(int type, String title, String desc, int i)
    {
        this.type=type;
        this.title=title;
        this.desc=desc;

    }

    //Конструктор с 5ю именами пользователей
    public Model(int type, List<User> users)
    {
        this.type=type;
        this.name1=users.get(0).getName();
        this.name2=users.get(1).getName();
        this.name3=users.get(2).getName();
        this.name4=users.get(3).getName();
        this.name5=users.get(4).getName();

    }

}
