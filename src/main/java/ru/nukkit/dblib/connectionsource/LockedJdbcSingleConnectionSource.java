package ru.nukkit.dblib.connectionsource;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.DatabaseConnection;

import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockedJdbcSingleConnectionSource extends JdbcConnectionSource {
    private static final Logger logger = LoggerFactory.getLogger(LockedJdbcSingleConnectionSource.class);
    private final Lock lock = new ReentrantLock(true);
    private DatabaseConnection connection;

    public LockedJdbcSingleConnectionSource() {
        // for spring type wiring
        super();
    }

    public LockedJdbcSingleConnectionSource(String url) throws SQLException {
        super(url);
    }

    public LockedJdbcSingleConnectionSource(String url, DatabaseType databaseType) throws SQLException {
        super(url, databaseType);
    }

    public LockedJdbcSingleConnectionSource(String url, String username, String password) throws SQLException {
        super(url, username, password);
    }

    public LockedJdbcSingleConnectionSource(String url, String username, String password, DatabaseType type) throws SQLException {
        super(url, username, password, type);
    }

    @Override
    public void initialize() throws SQLException {
        super.initialize();
        this.connection = this.makeConnection(logger);
    }

    @Override
    public void close() {
        // no-op because we don't want to close the connection
    }

    @Override
    public void releaseConnection(DatabaseConnection connection) {
        lock.unlock();
        // no-op because we don't want to close the connection
    }


    @Override
    public DatabaseConnection getReadWriteConnection(String tableName) throws SQLException {
        lock.lock();
        return this.connection;
    }
}