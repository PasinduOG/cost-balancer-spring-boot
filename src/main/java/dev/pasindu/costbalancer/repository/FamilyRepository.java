package dev.pasindu.costbalancer.repository;

public interface FamilyRepository {
    Integer createFamilyGroup(String familyName);
    String getFamilyName(Integer familyId);
}