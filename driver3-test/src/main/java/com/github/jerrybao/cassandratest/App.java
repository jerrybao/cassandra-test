package com.github.jerrybao.cassandratest;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 * Test Cassandra driver and connection.
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        new App().runTest();
    }

    public void runTest(){
        Cluster cluster = null;
        try {
            cluster = Cluster.builder()
                    .addContactPoints("localhost")
                    .withPort(9042)
                    .withCredentials("user", "pass")
                    .build();
            Session session = cluster.connect();

            ResultSet rs = session.execute("select release_version from system.local");
            Row row = rs.one();
            System.out.println(row.getString("release_version"));
        } finally {
            if (cluster != null) cluster.close();
        }

    }
}
