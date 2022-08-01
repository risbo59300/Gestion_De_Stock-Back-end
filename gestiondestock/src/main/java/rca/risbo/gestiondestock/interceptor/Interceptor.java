package rca.risbo.gestiondestock.interceptor;

import org.hibernate.EmptyInterceptor;
import org.springframework.util.StringUtils;

/**
 * @author ybgabamano
 * Cette méthode permet d'intercepter les réquêtes http entre les repository et la BDD
 */
public class Interceptor extends EmptyInterceptor {

    @Override
    public String onPrepareStatement(String sql) {
      if (StringUtils.hasLength(sql) && sql.toLowerCase().startsWith("select")) {
          if (sql.contains("where")){
              sql = sql + "and idEntreprise = 2";
          } else {
              sql = sql + "where idEntreprise = 2";
          }

      }
        return super.onPrepareStatement(sql);
    }
}
