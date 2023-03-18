package com.example.system.base.util;

import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.internal.InternalNode;
import org.neo4j.driver.internal.InternalRelationship;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Path;
import org.neo4j.driver.v1.types.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class Neo4jUtil {

    @Autowired
    private static Driver driver;


    /**
     * cql 路径查询 返回节点和关系
     *
     * @param cql      查询语句
     * @param nodeList 节点
     * @param edgeList 关系
     * @return List<Map < String, Object>>
     */
    public static <T> void getPathList(String cql, Set<T> nodeList, Set<T> edgeList) {
        try (
                Session session = driver.session();
        ) {
            StatementResult result = session.run(cql);
            List<Record> list = result.list();
            for (Record r : list) {
                for (String index : r.keys()) {
                    Path path = r.get(index).asPath();
                    //节点
                    Iterable<Node> nodes = path.nodes();
                    for (Iterator iter = nodes.iterator(); iter.hasNext(); ) {
                        InternalNode nodeInter = (InternalNode) iter.next();
                        Map<String, Object> map = new HashMap<>();
                        //节点id
                        map.put("nodeId", nodeInter.id());
                        //label
//                        map.put("nodeType",nodeInter.labels());
                        //节点上设置的属性
                        map.putAll(nodeInter.asMap());
                        nodeList.add((T) map);
                    }
                    //关系
                    Iterable<Relationship> edges = path.relationships();
                    for (Iterator iter = edges.iterator(); iter.hasNext(); ) {
                        InternalRelationship relationInter = (InternalRelationship) iter.next();
                        Map<String, Object> map = new HashMap<>();
                        map.putAll(relationInter.asMap());
                        //关系上设置的属性
                        map.put("edgeId", relationInter.id());
//                        map.put("edgeLabel", relationInter.type());
                        map.put("edgeFrom", relationInter.startNodeId());
                        map.put("edgeTo", relationInter.endNodeId());
                        edgeList.add((T) map);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static <T> void getPathListWithLabel(String cql, Set<T> nodeList, Set<T> edgeList) {
        try (
                Session session = driver.session();
        ) {
            StatementResult result = session.run(cql);
            List<Record> list = result.list();
            for (Record r : list) {
                for (String index : r.keys()) {
                    Path path = r.get(index).asPath();
                    //节点
                    Iterable<Node> nodes = path.nodes();
                    for (Iterator iter = nodes.iterator(); iter.hasNext(); ) {
                        InternalNode nodeInter = (InternalNode) iter.next();
                        Map<String, Object> map = new HashMap<>();
                        //节点id
                        map.put("id", nodeInter.id());
                        //label
                        map.put("label",nodeInter.labels());
                        //节点上设置的属性
                        map.putAll(nodeInter.asMap());
                        nodeList.add((T) map);
                    }
                    //关系
                    Iterable<Relationship> edges = path.relationships();
                    for (Iterator iter = edges.iterator(); iter.hasNext(); ) {
                        InternalRelationship relationInter = (InternalRelationship) iter.next();
                        Map<String, Object> map = new HashMap<>();
                        map.putAll(relationInter.asMap());
                        //关系上设置的属性
                        map.put("id", relationInter.id());
                        //label
                        map.put("label", relationInter.type());
                        map.put("from", relationInter.startNodeId());
                        map.put("to", relationInter.endNodeId());
                        edgeList.add((T) map);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static StatementResult excuteCQL(String cql) {
        try (
                Session session = driver.session();
        ) {
            StatementResult result = session.run(cql);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("执行CQL语句出错");
            return null;
        }
    }

}
