package service;

import dao.imp.UsersDaoIMP;
import enums.Enums;
import enums.Responese;
import onetooneentity.Users;

public class UserService {
    public UsersDaoIMP udimp = new UsersDaoIMP();

    public Responese Register(Users users) {
        udimp.Register(users);
        return new Responese(Enums.SUCCESS);
    }

    public void InsertUserKeyWord(String keyword, String uid) {
        if (keyword != null && uid != null) {
            Integer Uid = Integer.valueOf(uid);
            boolean come = true;
            Users users = udimp.selectUsersById(Uid);
            if (users.getUhotword() != null) {
                //有热词
                String uhotword = users.getUhotword();
                String[] split = uhotword.split("&");
                StringBuffer stringBuffer2 = new StringBuffer();
                for (String str :
                        split) {
                    if (keyword.equals(str)) {
                        //存在已有的关键字  将其放在最前面.
                        stringBuffer2.insert(0, str + "&");
                        come = false;
                    } else {
                        stringBuffer2.append(str + "&");
                    }
                }
                if (come) {
                    StringBuffer stringBuffer = new StringBuffer(uhotword);
                    StringBuffer append = stringBuffer.append(keyword + "&");
                    udimp.insertUserKeyWord(append.toString(), Uid);
                } else {
                    udimp.insertUserKeyWord(stringBuffer2.toString(), Uid);
                }

            } else {
                //没有热词
                udimp.insertUserKeyWord(keyword+"&", Uid);
            }


        }
    }

    public Responese Login(String email, String pwd) {
        Responese responese = new Responese(Enums.SUCCESS);
        Users login = udimp.Login(email, pwd);
        if (login.getUname() != null && login.getUpassword() != null) {
            responese.setObj(login);
            return responese;
        } else {
            return new Responese(Enums.FAIL);
        }
    }

    public Responese selectUserByUnameAndUemail(String uname, String uemail) {
        Responese responese = null;
        if (uname != null && uemail != null) {
            String s = udimp.selectPwdWordByEmailAndUname(uemail, uname);
            if (s != null) {
                responese = new Responese(Enums.SUCCESS);
                responese.setObj(s);
            } else {
                responese = new Responese(Enums.FAIL);
            }
        } else {
            responese = new Responese(Enums.FAIL);
        }
        return responese;
    }

    public Responese modifyUserByUnameAndUemail(String uname, String uemail, String newpwd) {
        Responese responese = new Responese(Enums.SUCCESS);
        if (uname != null && uemail != null && newpwd != null) {
            Users users = new Users();
            users.setUemail(uemail);
            users.setUname(uname);
            users.setUpassword(newpwd);
            udimp.modifyUserByUnameAndUemail(users);
            return responese;
        } else {
            return new Responese(Enums.FAIL);
        }
    }

    public Responese existUphone(String Uphone) {
        Responese responese = new Responese(Enums.SUCCESS);
        if (Uphone != null) {
            if (udimp.existPhone(Uphone)) {
                return responese;
            } else {
                return new Responese(Enums.FAIL);
            }
        } else {
            return new Responese(Enums.FAIL);
        }
    }

    public Responese existUemail(String Uemail) {
        Responese responese = new Responese(Enums.SUCCESS);
        if (Uemail != null) {
            if (udimp.existEmail(Uemail)) {
                return responese;
            } else {
                return new Responese(Enums.FAIL);
            }
        } else {
            return new Responese(Enums.FAIL);
        }
    }

    public Responese selectUserByUname(String Uname) {
        Responese responese = new Responese(Enums.SUCCESS);
        if (Uname != null) {
            Users users = udimp.selectUsersByUname(Uname);
            responese.setObj(users);
            return responese;
        } else {
            return new Responese(Enums.FAIL);
        }


    }

//
//    public Set<Users> getAllUser() throws SQLException {
//        return udimp.selectAllUsers();
//    }
//    public Responese getUserByPhone(Scanner sc) throws SQLException {
//        Responese rs = new Responese(Enums.SUCCESS);
//        Set<Users> selectAllUsers = udimp.selectAllUsers();
//        System.out.println("请输入账号： ");
//        String uName = sc.next();
//        System.out.println("请输入密码： ");
//        String uPwd = sc.next();
//
//        for (Users users : selectAllUsers) {
//
//            if (users.getUname().equals(uName)) {
//                if (users.getUphone().equals(uPwd)) {
//                    rs.setObj(users);
//                    return rs;
//                }
//            }
//        }
//        return new Responese(Enums.NULL);
//    }

    //    public Integer getpower(Users u) throws SQLException {
//        Set<Users> selectAllUsers = udimp.selectAllUsers();
//        for (Users users : selectAllUsers) {
//            if (users.getUname().equals(u.getUphone())) {
//                return 0;
//            } else {
//                return 1;
//            }
//        }
//
//        return null;
//
//    }
    public Responese selectbyUid(Integer uid) {
        Responese responese = new Responese(Enums.SUCCESS);
        Users users = udimp.selectUsersById(uid);
        if (users.getUid() == uid) {
            responese.setObj(users);
            return responese;
        } else {
            return new Responese(Enums.NULL);
        }


    }
}
