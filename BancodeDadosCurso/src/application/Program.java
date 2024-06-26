package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {

		
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = DB.getConnection();
			
			//Criando uma transação, para que todo o comando seja executado no banco de dados ou nenhum
			
			conn.setAutoCommit(false); //Toda operação necessita de validação pelo progrmador
			
			st = conn.createStatement();
			
			int linhas1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			
			int x = 1;
			
			//if(x<2) {
				//throw new SQLException("Fake error");
			//}
			
			int linhas2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			
			conn.commit();//Informando que a transação terminou
			
			System.out.println("Comando 1 - Linhas alteradas: " + linhas1);
			System.out.println("Comando 2 - Linhas alteradas: " + linhas2);
		}
		catch(SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transação não concluida! Erro: "+e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Erro ao voltar a transação! Erro" + e1.getMessage());
			}
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
		
	}
}