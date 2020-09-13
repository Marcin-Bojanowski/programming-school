package programming.school.dao;

import programming.school.model.Exercise;
import programming.school.model.Solution;
import programming.school.model.User;
import programming.school.utils.DbUtils;

import java.sql.*;
import java.util.Arrays;

public class SolutionsDao {
    private static final String CREATE_SOLUTION_SQL = "insert into solutions (exercises_id, user_id, created)\n" +
            "values (?,?,?)";
    private static final String SELECT_SOLUTION_BY_ID = "SELECT * FROM solutions where id = ? and user_id=?";
    private static final String UPDATE_SOLUTION_QUERY =
            "UPDATE solutions SET updated = ?, description = ? where id = ?";
    private static final String DELETE_EXERCISE_QUERY =
            "DELETE FROM exercises WHERE id = ?";
    private static final String FIND_ALL_SOLUTIONS_BY_USER_ID =
            "SELECT * FROM solutions where user_id=?";

    public void create(Solution solution) {
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_SOLUTION_SQL)) {
            statement.setInt(1, solution.getExerciseId());
            statement.setInt(2, solution.getUserId());
            statement.setTimestamp(3, solution.getCreated());
            int rowsAffected = statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public Solution readById(int id, User user) {
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_SOLUTION_BY_ID)) {
            statement.setInt(1, id);
            statement.setInt(2,user.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Timestamp created = resultSet.getTimestamp("created");
                    String description = resultSet.getString("description");
                    int exericesId = resultSet.getInt("exercises_id");
                    int userId = resultSet.getInt("user_id");
                    Timestamp updated = resultSet.getTimestamp("updated");
                    Solution solution = new Solution(id, exericesId, userId, created, updated, description);
                    return solution;
                }


            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void update(Solution solution) {
        try (Connection conn = DbUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
            statement.setTimestamp(1, solution.getUpdated());
            statement.setString(2, solution.getDescription());
            statement.setInt(3, solution.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Solution[] addToArray(Solution solution, Solution[] solutions) {
        Solution[] tmpSolutions = Arrays.copyOf(solutions, solutions.length + 1);
        tmpSolutions[solutions.length] = solution;
        return tmpSolutions;
    }

    public Solution[] findAll(int id) {
        try (Connection conn = DbUtils.getConnection()) {
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_BY_USER_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();

                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTimestamp("created"));
                solution.setExerciseId(resultSet.getInt("exercises_id"));
                solution.setUserId(resultSet.getInt("user_id"));
                solution.setUpdated(resultSet.getTimestamp("updated"));
                solution.setDescription(resultSet.getString("description"));
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
