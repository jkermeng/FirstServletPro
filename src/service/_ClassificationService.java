package service;

import dao.imp._ClassificationDaoIMP;
import enums.Enums;
import enums.Responese;
import onetomanyentity._Classification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class _ClassificationService {
    private _ClassificationDaoIMP cf = new _ClassificationDaoIMP();

    public Responese selectAll() {
        Responese responese = new Responese(Enums.SUCCESS);
        Set<_Classification> selectall = cf.selectall();
        List<_Classification> list = new ArrayList<>();
        for (_Classification cft :
                selectall) {
            list.add(cft);
        }
        list.sort(new Comparator<_Classification>() {
            @Override
            public int compare(_Classification o1, _Classification o2) {
                return o1.getCid() - o2.getCid();
            }
        });
        responese.setObj(list);
        return responese;
    }

    public Responese selectById(Integer cid) {
        Responese responese = new Responese(Enums.SUCCESS);
        return responese;
    }

    public Responese insert() {
        Responese responese = new Responese(Enums.SUCCESS);
        return responese;
    }

    public Responese delete() {
        Responese responese = new Responese(Enums.SUCCESS);
        return responese;
    }

    public Responese update() {
        Responese responese = new Responese(Enums.SUCCESS);
        return responese;
    }
}
