package ubicamovil.appweb.utils;

import java.io.IOException;
import jakarta.servlet.ServletException;

public interface IAuthorize {
    void authorize() throws ServletException, IOException;
}
