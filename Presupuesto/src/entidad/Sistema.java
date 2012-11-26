package entidad;

import java.util.ArrayList; 

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.6552EF43-3A12-FC3C-FA4C-8C5F17D1CFFB]
// </editor-fold> 
public class Sistema {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F397CC62-B045-2445-AAA1-3FA244D54D74]
    // </editor-fold> 
    public static Sistema msistema=new Sistema(); //tenemos un unico sistema
    private ArrayList<Presupuesto> mPresupuesto;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1B78DD78-4CF1-0AAF-3CD8-A873D95F9A0C]
    // </editor-fold> 
    public Sistema () {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.6970FBEF-A687-9AD7-6891-6E337B4503EE]
    // </editor-fold> 
    public ArrayList<Presupuesto> getPresupuesto () {
        return mPresupuesto;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.99545B6A-3B8C-E8D3-A2AA-BD9DF41EB523]
    // </editor-fold> 
    public void setPresupuesto (ArrayList<Presupuesto> val) {
        this.mPresupuesto = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6043FD3E-F2F1-09A4-C5CD-4250641E0390]
    // </editor-fol

}

