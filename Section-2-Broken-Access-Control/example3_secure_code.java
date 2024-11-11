@WebServlet("/admin/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedUser");

        // Check if the user is an administrator
        if (loggedInUser != null && loggedInUser.isAdmin()) {
            String userId = request.getParameter("userId");
            UserDAO.deleteUser(userId);
            response.getWriter().write("User deleted successfully.");
        } else {
            response.getWriter().write("Unauthorized attempt to delete user.");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}

