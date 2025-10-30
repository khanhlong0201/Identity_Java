package java_learn.identity.core.common.interfaces;

import java_learn.identity.core.starter.database.Auditable;

public interface IAuditableListeners {
    void beforeAnyUpdate(Auditable auditable);

    void afterAnyUpdate(Auditable auditable);

    void afterLoad(Auditable auditable);

    void beforeRemove(Auditable auditable);

    void afterRemove(Auditable auditable);
}