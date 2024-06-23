package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbExcecaoIntegridade;

public class Program {

	public static void main(String[] args) {

		
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"DELETE FROM department "+
					"WHERE "+
					"(Id = ?)");
			
			st.setInt(1, 2);
			
			int linhasAlteradas = st.executeUpdate();
			
			System.out.println("Pronto - Linhas alteradas: " + linhasAlteradas);
		}
		catch(SQLException e) {
			throw new DbExcecaoIntegridade(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
		
	}
}