package pecaseletronicas.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

    // ======================
    // CONFIGURAÇÕES FIXAS
    // ======================
    private static final String NOME_PASTA = "pecasEletronicas1";
    private static final String NOME_DB = "dbSqlite.db";

    // SQL de criação de tabela
    private static final String SQL_CREATE_TABLE_PECAS = " CREATE TABLE IF NOT EXISTS pecas ( id INTEGER PRIMARY KEY AUTOINCREMENT, peca TEXT NOT NULL, tipo TEXT NOT NULL, caixa TEXT, obs TEXT );";

    // ======================
    // MÉTODO PRINCIPAL
    // ======================
    public static Connection conector() {

        try {
            // 1. Pasta do usuário
            String userHome = System.getProperty("user.home");
            File pastaApp = new File(userHome, NOME_PASTA);

            // 2. Cria a pasta se não existir
            if (!pastaApp.exists()) {
                pastaApp.mkdirs();
            }

            // 3. Arquivo do banco
            File dbFile = new File(pastaApp, NOME_DB);

            // 4. Verifica se o banco já existia
            boolean bancoNovo = !dbFile.exists();

            // 5. Abre conexão (cria o banco se não existir)
            String url = "jdbc:sqlite:" + dbFile.getAbsolutePath();
            Connection conn = DriverManager.getConnection(url);

            // 6. Se o banco for novo, cria estrutura
            if (bancoNovo) {
                inicializarBanco(conn);
            }

            return conn;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }

    // ======================
    // INICIALIZAÇÃO DO BANCO
    // ======================
    private static void inicializarBanco(Connection conn) {

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(SQL_CREATE_TABLE_PECAS);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inicializar o banco", e);
        }
    }
}
