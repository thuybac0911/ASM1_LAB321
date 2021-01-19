/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacltt.daos;

import bacltt.dtos.CateDTO;
import bacltt.utils.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thúy Bắc
 */
public class CateDAO implements Serializable{
    private Connection conn;
    private PreparedStatement stm;
    private ResultSet rs;
    
    private void closeConnection()  throws Exception{
        if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
    }

    public CateDAO() {
    }
    
    public List<CateDTO> fillAllCate() throws Exception{
        List<CateDTO> result = null;
        String id, name;
        CateDTO dto=null;
        try {
            String sql = "SELECT CateID,CateName "
                    + "FROM tblCategories";
            conn = DBUtil.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            result= new ArrayList<>();
            while(rs.next()){
                id = rs.getString("CateID");
                name = rs.getString("CateName");
                dto = new CateDTO(id, name);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
