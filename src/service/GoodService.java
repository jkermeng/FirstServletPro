package service;

import dao.imp.GoodsDaoIMP;
import dao.imp.GoodsPictureDaoIMP;
import dao.imp._ClassificationDaoIMP;
import entity.HotProduce;
import entity.PageModel;
import enums.Enums;
import enums.Responese;
import onetomanyentity.Goods;
import onetomanyentity.GoodsPicture;
import onetomanyentity._Classification;
import onetooneentity.Users;
import sun.plugin.javascript.navig.Link;

import java.sql.SQLException;
import java.util.*;

public class GoodService {
    private GoodsDaoIMP gdimp = new GoodsDaoIMP();
    private GoodsPictureDaoIMP gpdimp = new GoodsPictureDaoIMP();
    private _ClassificationDaoIMP _cfdimp = new _ClassificationDaoIMP();

    public List<Goods> selectGoodsGid() {
        return gdimp.selectAllGid();
    }

    public PageModel pageGoods(Integer showItems, Integer pages) {
        PageModel pageModel = new PageModel();
        List<HotProduce> listgoods = new ArrayList<>();//货物
        List listpage = new LinkedList();//分页
        pageModel.setCurrentPage(pages);//当前页面数
        List<Goods> goods = gdimp.selectAllGid();
        goods.sort(new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                return o1.getGid() - o2.getGid();
            }
        });
        int gsize = goods.size();//货物总数
        int totalPage = (int) Math.ceil((double) gsize / showItems);        //分页总数
        pageModel.setTotalPage(totalPage);
        int setpage = 1;
        while (setpage <= totalPage) {
            listpage.add(setpage);
            setpage++;
        }
        pageModel.setPageList(listpage);

        if (pages == 1) {
            for (int i = 0; i < showItems; i++) {
                Goods goods1 = gdimp.selectById(goods.get(i).getGid());//货物
                GoodsPicture goodsPicture = gpdimp.selectByGId((goods.get(i).getGid()));
                goods1.setGp(goodsPicture);
                HotProduce hotProduce = new HotProduce();
                hotProduce.setHpId(goods1.getGid());
                hotProduce.setHpName(goods1.getGname());
                hotProduce.setHpPrice(goods1.getGprice());
                hotProduce.setHpFileName(goodsPicture.getPurl());
                listgoods.add(hotProduce);
            }
        } else {
            int showtatol = showItems * pages;
            int show = showtatol - showItems;
            for (int i = show; i < showtatol; i++) {
                if (i < gsize) {
                    Goods goods1 = gdimp.selectById(goods.get(i).getGid());//货物
                    GoodsPicture goodsPicture = gpdimp.selectByGId((goods.get(i).getGid()));
                    goods1.setGp(goodsPicture);
                    HotProduce hotProduce = new HotProduce();
                    hotProduce.setHpId(goods1.getGid());
                    hotProduce.setHpName(goods1.getGname());
                    hotProduce.setHpPrice(goods1.getGprice());
                    hotProduce.setHpFileName(goodsPicture.getPurl());
                    listgoods.add(hotProduce);
                } else {
                    break;
                }
            }
        }
        pageModel.setList(listgoods);
        return pageModel;
    }

    public List<Goods> getGoodsData() {
        Set<Goods> goods = gdimp.select(null);
        List<Goods> glist = new ArrayList<>();
        for (Goods gs : goods) {
            _Classification cId = _cfdimp.selectById(gs.getSetClassfy().getCid());
            gs.setSetClassfy(cId);
            glist.add(gs);
        }
        glist.sort(new Comparator<Goods>() {

            @Override
            public int compare(Goods o1, Goods o2) {
                return o1.getGid() - o2.getGid();
            }
        });
        return glist;
    }

    public Responese getGoodsByClassifyId(Integer cid) {
        Responese responese = new Responese(Enums.SUCCESS);
        List<Goods> list = new ArrayList();
        List<Goods> goods = gdimp.selectByClassficationId(cid);
        if (cid != null && cid != 0) {
            for (Goods g :
                    goods) {
                list.add(g);
            }

            list.sort(new Comparator<Goods>() {
                @Override
                public int compare(Goods o1, Goods o2) {
                    return o1.getGid() - o2.getGid();
                }
            });
            responese.setObj(list);
            return responese;
        } else {
            return new Responese(Enums.FAIL);
        }
    }

    public List<Goods> selectByGoodsName(String gname) {
        List<Goods> gs = new LinkedList<>();
        if (gname != null) {
            Set<Goods> goods = gdimp.selectByGname(gname);
            for (Goods g :
                    goods) {
                gs.add(g);
            }
            return gs;
        } else {
            return null;
        }
    }

    public Goods getGoodsDataById(int id) {

        Goods selectById = gdimp.selectById(id);

        return selectById;
    }

    public List<_Classification> getClassificationData() throws SQLException {
        List<_Classification> cflist = new ArrayList<>();
        Set<_Classification> selectall = _cfdimp.selectall();
        for (_Classification cfs : selectall) {
            List<Goods> cfid = gdimp.selectByClassficationId(cfs.getCid());
            cfs.setSetgoods(cfid);
            cflist.add(cfs);
        }
        cflist.sort(new Comparator<_Classification>() {
            @Override
            public int compare(_Classification o1, _Classification o2) {
                return o1.getCid() - o2.getCid();
            }
        });
        return cflist;
    }

    public Responese addGoods(Goods t, Users u) throws SQLException {
        if (u.getUname().equals("管理员")) {
            gdimp.insert(t);
            return new Responese(Enums.SUCCESS);

        } else {
            return new Responese(Enums.FAIL);
        }

    }

    public Responese updateGoods(Goods t, Users u) throws SQLException {

        if (u.getUname().equals("管理员")) {
            gdimp.update(t);
            return new Responese(Enums.SUCCESS);

        } else {
            return new Responese(Enums.FAIL);
        }
    }

    public Responese deleteGoods(Goods t, Users u) throws SQLException {
        if (u.getUname().equals("管理员")) {
            gdimp.delete(t);
            return new Responese(Enums.SUCCESS);

        } else {
            return new Responese(Enums.FAIL);
        }
    }
}
