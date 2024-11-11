@WebServlet("/admin/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("userId");
        UserDAO.deleteUser(userId);
        response.getWriter().write("User deleted successfully.");
    }
}

