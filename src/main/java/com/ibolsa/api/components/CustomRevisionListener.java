package com.ibolsa.api.components;

import com.ibolsa.api.domain.auditoria.CustomRevisionEntity;
import org.hibernate.envers.RevisionListener;
import org.springframework.stereotype.Component;

@Component
public class CustomRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object arg0) {
        CustomRevisionEntity custom = (CustomRevisionEntity) arg0;
        custom.setUsuarioNome("ADMINISTRADOR");
        custom.setUsuarioId(null);
//        final UserContext userContext = UserContextHolder.getUserContext();
        custom.setIp(null);
    }

//    @Override
//    public void newRevision(Object revisionEntity) {
//        CustomRevisionEntity customRevisionEntity = (CustomRevisionEntity) revisionEntity;
//        EntityManagerFactory emf = ContextLookup.getApplicationContext().getBean(EntityManagerFactory.class);
//        AuditReader auditReader = AuditReaderFactory.get(emf.createEntityManager());
//        Number resultList = null;
//        Object obj = ContextLookup.getCurrentEntity();
//        if( Employee.class.isInstance(obj)) {
//            AuditQuery query = auditReader.createQuery()
//                    .forRevisionsOfEntity(Department.class, false, true)
//                    .addProjection(AuditEntity.revisionNumber().max());
//            resultList = (Number) query.getSingleResult();
//        }
//        customRevisionEntity.setRefRevisionId(resultList != null?resultList.longValue():0);
//    }
}
