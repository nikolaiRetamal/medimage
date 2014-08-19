package cnam.medimage.service;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.NoHostAvailableException;

public class CassandraConnection {

    private static CassandraConnection _instance;
    protected static Cluster cluster;
    protected static Session session;


    public static synchronized CassandraConnection getInstance() {
        if (_instance == null) {
            _instance = new CassandraConnection();
        }
        return _instance;
    }

    /**
     * Creating Cassandra connection using Datastax API
     *
     */
    private CassandraConnection() {

        try{
            cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
            session = cluster.connect("medimage");           
        } catch (NoHostAvailableException e) {
            throw new RuntimeException(e);
        }
    }

    public static Cluster getCluster() {
        return cluster;
    }

    public static Session getSession() {
        return session;
    }
}