package monitoring.webapp.service;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;

import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.Server;
import org.hsqldb.server.ServerAcl.AclFormatException;

public class LoggingDB {

    private java.sql.Connection connection;

    public void startup() throws SQLException, ServletException {
        startUpLogDBServer();

        establishLogDBConnection();

        initializeLogDB();

    }

    public void shutdown() throws SQLException {

        Statement statement = connection.createStatement();
        statement.execute("SHUTDOWN");
        connection.close();
    }

    public void establishLogDBConnection() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");

            connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001/logdb", "sa", ""); // password

        } catch (Exception e) {
            System.out.println("Error creating connection to logging db.");
        }
    }

    private void initializeLogDB() throws SQLException {
        Statement statement = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE logging_event_exception IF EXISTS;");
            statement.executeUpdate("DROP TABLE logging_event_property IF EXISTS;");
            statement.executeUpdate("DROP TABLE logging_event IF EXISTS;");

            statement.executeUpdate("CREATE TABLE logging_event (timestmp BIGINT NOT NULL,"
                    //
                    + " formatted_message LONGVARCHAR NOT NULL,"
                    //
                    + " logger_name VARCHAR(256) NOT NULL,"
                    //
                    + " level_string VARCHAR(256) NOT NULL,"
                    //
                    + " thread_name VARCHAR(256),"
                    //
                    + " reference_flag SMALLINT, "
                    //
                    + " arg0 VARCHAR(256), "

                    + " arg1 VARCHAR(256), "
                    //
                    + " arg2 VARCHAR(256), "
                    //
                    + " arg3 VARCHAR(256), "
                    //
                    + " caller_filename VARCHAR(256), "
                    //
                    + " caller_class VARCHAR(256), "
                    //
                    + " caller_method VARCHAR(256), "
                    //
                    + " caller_line CHAR(4), "
                    //
                    + " event_id BIGINT NOT NULL IDENTITY);");

            statement.executeUpdate("CREATE TABLE logging_event_property ("
                    //
                    + " event_id BIGINT NOT NULL,"
                    //
                    + " mapped_key  VARCHAR(254) NOT NULL,"
                    //
                    + " mapped_value LONGVARCHAR, "
                    //
                    + " PRIMARY KEY(event_id, mapped_key),"
                    //
                    + " FOREIGN KEY (event_id) REFERENCES logging_event(event_id));");

            statement.executeUpdate("CREATE TABLE logging_event_exception ("
                    //
                    + " event_id BIGINT NOT NULL, "
                    //
                    + " i SMALLINT NOT NULL, "
                    //
                    + " trace_line VARCHAR(256) NOT NULL,"
                    //
                    + " PRIMARY KEY(event_id, i),"
                    //
                    + " FOREIGN KEY (event_id) REFERENCES logging_event(event_id));");

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    private void startUpLogDBServer() throws ServletException {
        try {
            System.out.println("Starting Logging Database");
            HsqlProperties p = new HsqlProperties();
            p.setProperty("server.database.0", "file:D:/WorkspaceTU_Master/alligatorbirne/workbench/logDB/db/file");
            p.setProperty("server.dbname.0", "logdb");
            p.setProperty("server.port", "9001");
            Server server = new Server();
            server.setProperties(p);
            server.setLogWriter(null); // can use custom writer
            server.setErrWriter(null); // can use custom writer
            server.start();
        } catch (AclFormatException afex) {
            throw new ServletException(afex);
        } catch (IOException ioex) {
            throw new ServletException(ioex);
        }
    }

    public java.sql.Connection getConnection() {
        return connection;
    }

    public boolean isConnection() {
        if (null == connection) {
            this.establishLogDBConnection();

            if (null == connection) {
                return false;
            }
        }
        return true;
    }

}
