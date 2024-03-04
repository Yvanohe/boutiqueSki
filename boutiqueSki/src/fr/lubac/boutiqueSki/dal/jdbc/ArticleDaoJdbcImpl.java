package fr.lubac.boutiqueSki.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.lubac.boutiqueSki.bo.Article;
import fr.lubac.boutiqueSki.bo.Combinaison;
import fr.lubac.boutiqueSki.bo.Ski;
import fr.lubac.boutiqueSki.dal.ArticleDAO;

public class ArticleDaoJdbcImpl implements ArticleDAO {
    private static final String TABLE_NAME = "Articles";

    // ---------
    // SQL QUERY
    // ---------
    private static final String sqlSelectById = "select idArticle, reference, marque, designation, prixUnitaire, qteStock, longueur, couleur, type "
            + "from " + TABLE_NAME + " where idArticle = ?";
    private static final String sqlSelectAll = "SELECT * from Articles";
    private static final String sqlUpdate = "update articles set reference=?, marque=?, designation=?, prixUnitaire=?, qteStock=?, longueur=?, couleur=? WHERE idArticle=?";
    private static final String sqlInsert = "INSERT INTO " + TABLE_NAME
            + "(reference,marque,designation,prixUnitaire,qteStock,longueur,couleur, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String sqlDelete = "DELETE FROM " + TABLE_NAME + " WHERE idArticle=?";

    // Constructor
    public ArticleDaoJdbcImpl() {
    }

    public Article selectById(int id) {
        PreparedStatement pstmt = null;
        Connection con = null;
        Article article = null;

        try {
            con = JdbcTools.getConnection();
            // Prepare Statement :
            pstmt = con.prepareStatement(sqlSelectById);
            pstmt.setInt(1, id);
            // execute request :
            ResultSet rs = pstmt.executeQuery();
            // should have only one result so take the first anyway :
            if (rs.next()) {
                String type = rs.getString("type").trim();

                // Instanciate a new Article object :
                switch (type) {
                    case "Ski":
                        article = new Ski(id, rs.getString("marque"), rs.getString("reference"),
                                rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
                                rs.getInt("longueur"));
                        break;

                    case "Combi":
                        article = new Combinaison(id, rs.getString("marque"), rs.getString("reference"),
                                rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
                                rs.getString("couleur"));
                        break;
                    default:
                        System.out.println("Rien trouvé pour : " + type);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                JdbcTools.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return article;
    }

    public List<Article> selectAll() {
        List<Article> listArticles = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;

        try {
            con = JdbcTools.getConnection();
            // Create Statement :
            stmt = con.createStatement();
            // execute request :
            ResultSet rs = stmt.executeQuery(sqlSelectAll);
            // browse cursor :
            while (rs.next()) {
                String type = rs.getString("type").trim();
                switch (type) {
                    case "Ski":
                        Ski ski = new Ski(rs.getInt("idArticle"), rs.getString("marque"), rs.getString("reference"),
                                rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
                                rs.getInt("longueur"));
                        listArticles.add(ski);
                        break;
                    case "Combi":
                        Combinaison combinaison = new Combinaison(rs.getInt("idArticle"), rs.getString("marque"),
                                rs.getString("reference"),
                                rs.getString("designation"), rs.getFloat("prixUnitaire"), rs.getInt("qteStock"),
                                rs.getString("couleur"));
                        listArticles.add(combinaison);
                        break;

                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                JdbcTools.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listArticles;
    }

    public void update(Article article) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = JdbcTools.getConnection();
            pstmt = con.prepareStatement(sqlUpdate);

            pstmt.setString(1, article.getReference());
            pstmt.setString(2, article.getMarque());
            pstmt.setString(3, article.getDesignation());
            pstmt.setFloat(4, article.getPrixUnitaire());
            pstmt.setInt(5, article.getQteStock());
            pstmt.setInt(8, article.getIdArticle());

            if (article instanceof Ski) {
                Ski ski = (Ski) article;
                pstmt.setInt(6, ski.getLongueur());
                pstmt.setNull(7, Types.VARCHAR);

            } else if (article instanceof Combinaison) {
                Combinaison combi = (Combinaison) article;
                pstmt.setNull(6, Types.INTEGER);
                pstmt.setString(7, combi.getCouleur());
            }

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                JdbcTools.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void insert(Article article) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = JdbcTools.getConnection();
            pstmt = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, article.getReference());
            pstmt.setString(2, article.getMarque());
            pstmt.setString(3, article.getDesignation());
            pstmt.setFloat(4, article.getPrixUnitaire());
            pstmt.setInt(5, article.getQteStock());

            if (article instanceof Ski) {
                Ski ski = (Ski) article;
                pstmt.setInt(6, ski.getLongueur());
                pstmt.setNull(7, Types.VARCHAR);
                pstmt.setString(8, "Ski");
            } else if (article instanceof Combinaison) {
                Combinaison combi = (Combinaison) article;
                pstmt.setNull(6, Types.INTEGER);
                pstmt.setString(7, combi.getCouleur());
                pstmt.setString(8, "Combi");
            }

            int nbRows = pstmt.executeUpdate();
            if (nbRows == 1) {
                // get the unique ID generated when inserting article in DB and update Article
                // object with it
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    article.setIdArticle(rs.getInt(1));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                JdbcTools.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(int id) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = JdbcTools.getConnection();
            // Prepare Statement :
            pstmt = con.prepareStatement(sqlDelete);
            pstmt.setInt(1, id);
            // executre statement :
            pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                JdbcTools.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
