package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.service.AuthorService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "author", urlPatterns = "/author")
public class AuthorServlet extends HttpServlet {
    private final AuthorService authorService;
    public static String isbn;
    private final SpringTemplateEngine springTemplateEngine;

    public AuthorServlet(AuthorService authorService, SpringTemplateEngine springTemplateEngine) {
        this.authorService = authorService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);


        WebContext context = new WebContext(webExchange);
        context.setVariable("authors", authorService.listAuthors());
        isbn = req.getParameter("bookIsbn");
        context.setVariable("isbn", isbn);
        springTemplateEngine.process("authorList.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
