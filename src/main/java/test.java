import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE"; // 오라클 서버 URL
    private static final String USER = "c##sqluser"; // 데이터베이스 사용자 이름
    private static final String PASSWORD = "1234"; // 데이터베이스 사용자 비밀번호

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // 드라이버 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 데이터베이스 연결
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("데이터베이스 연결 성공!");
        } catch (ClassNotFoundException e) {
            System.err.println("드라이버를 찾을 수 없습니다: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("데이터베이스 연결 실패: " + e.getMessage());
        } finally {
            // 연결 해제
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("연결을 닫는 중 오류 발생: " + e.getMessage());
                }
            }
        }
    }
}
