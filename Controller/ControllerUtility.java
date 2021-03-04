package PracticeAfterLearn.Chuong2.Bai4.Controller;

import PracticeAfterLearn.Chuong2.Bai4.Model.BRM;

import java.util.ArrayList;

public class ControllerUtility {
    public static ArrayList<BRM> upDateFile(BRM brm, ArrayList<BRM> brms) {
        boolean isUpdate = false;
        for (int i = 0; i < brms.size(); ++i) {
            if (brm.getReader().getReaderID() == brms.get(i).getReader().getReaderID()
                    && brm.getBook().getBookID() == brms.get(i).getBook().getBookID()) {
                isUpdate = true;
                brms.set(i, brm);
            }
        }
        if (isUpdate == false) {
            brms.add(brm);
        }
        return brms;
    }

    public ArrayList<BRM> getTotal(ArrayList<BRM> brms) {
        BRM brm;
        for (int i = 0; i < brms.size(); ++i) {
            brm = brms.get(i);
            int count = 0;
            for (int j = 0; j < brms.size(); ++j) {
                if (brm.getReader().getReaderID() == brms.get(j).getReader().getReaderID()) {
                    count += brms.get(j).getNumOfBorrow();
                }
                brm.setTotal(count);
                brms.set(i, brm);
            }
        }
        return brms;

    }

    public void SortByName(ArrayList<BRM> brms) {
        for (int i = 0; i < brms.size(); ++i) {
            for (int j = brms.size() - 1; j > i; j--) {
                BRM bj = brms.get(j);
                BRM bbj = brms.get(j - 1);
                if (bbj.getReader().getFullName().
                        substring(bbj.getReader().getFullName().lastIndexOf(" "))
                        .compareTo(bj.getReader().getFullName().
                                substring(bj.getReader().getFullName().lastIndexOf(" "))) > 0) {
                    brms.set(j - 1, bj);
                    brms.set(j, bbj);
                }
            }
        }
    }

    public void SortByTotalOfBorrow(ArrayList<BRM> brms) {
        for (int i = 0; i < brms.size(); ++i) {
            for (int j = brms.size() - 1; j > i; j--) {
                BRM bj = brms.get(j);
                BRM bbj = brms.get(j - 1);
                if (bj.getTotal() < bbj.getTotal()) {
                    brms.set(j - 1, bj);
                    brms.set(j, bbj);
                }
            }
        }
    }

    public ArrayList<BRM> searchName(String key, ArrayList<BRM> brms) {
        var result = new ArrayList<BRM>();
        String regex = ".*" + key.toLowerCase() + ".*";
        for (int i = 0; i < brms.size(); ++i) {
            if(brms.get(i).getReader().getFullName().toLowerCase().matches(regex)){
                result.add(brms.get(i));
            }
        }
        return result;
    }
}
