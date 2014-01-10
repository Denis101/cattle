package io.github.ibuildthecloud.dstack.object.meta;

public interface Relationship {

    enum RelationshipType {
        CHILD, REFERENCE
    }

    boolean isListResult();

    RelationshipType getRelationshipType();

    String getName();

    String getPropertyName();

    Class<?> getObjectType();

}
