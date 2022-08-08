package rca.risbo.gestiondestock.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rca.risbo.gestiondestock.dto.CategoryDto;
import rca.risbo.gestiondestock.exception.EntityNotFoundException;
import rca.risbo.gestiondestock.exception.ErrorCodes;
import rca.risbo.gestiondestock.exception.InvalidEntityException;
import rca.risbo.gestiondestock.services.CategoryService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void  shouldSaveCategoryWithSuccess() {
        //GIVEN
        CategoryDto expectedCategory = CategoryDto.builder()
            .code("Cat test")
            .designation("Designation test")
            .idEntreprise(1)
            .build();

        // WHEN
        CategoryDto savedCategory = categoryService.save(expectedCategory);

        // THEN
        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getId()).isNotNull();
        assertThat(expectedCategory.getCode()).isEqualTo(savedCategory.getCode());
        assertThat(savedCategory.getDesignation()).isEqualTo(savedCategory.getDesignation());
        assertThat(savedCategory.getIdEntreprise()).isEqualTo(savedCategory.getIdEntreprise());
    }

    @Test
    public void  shouldUpdateCategoryWithSuccess() {
        CategoryDto expectedCategory = CategoryDto.builder()
                .code("Cat test")
                .designation("Designation test")
                .idEntreprise(1)
                .build();

        CategoryDto savedCategory = categoryService.save(expectedCategory);

        CategoryDto updatedCategory = categoryService.save(savedCategory);
        updatedCategory.setCode("Cat Updated");

        savedCategory = categoryService.save(updatedCategory);

        assertThat(updatedCategory).isNotNull();
        assertThat(updatedCategory.getId()).isNotNull();
        assertThat(updatedCategory.getCode()).isEqualTo(savedCategory.getCode());
        assertThat(updatedCategory.getDesignation()).isEqualTo(savedCategory.getDesignation());
        assertThat(updatedCategory.getIdEntreprise()).isEqualTo(savedCategory.getIdEntreprise());
    }

    @Test
    public void  shouldThrowInvalidEntityException() {
        CategoryDto expectedCategory = CategoryDto.builder().build();

        InvalidEntityException expectedException = assertThrows(InvalidEntityException.class,
            () -> categoryService.save(expectedCategory));

        assertThat(ErrorCodes.CATEGORY_NOT_VALID).isEqualTo(expectedException.getErrorCodes());
        assertThat(expectedException.getErrors().size()).isEqualTo(1);
        assertThat(expectedException.getErrors().get(0)).isEqualTo("Veuillez renseigner le code de la categorie");
    }

    @Test
    public void  shouldThrowEntityNotFoundException() {

        EntityNotFoundException expectedException = assertThrows(EntityNotFoundException.class,
                () -> categoryService.findById(0));

        assertThat(ErrorCodes.CATEGORY_NOT_FOUND).isEqualTo(expectedException.getErrorCodes());
        assertThat(expectedException.getMessage()).isEqualTo("Aucune category avec l'ID = " + 0 + " n' ete trouve dans la BDD");
    }

    @Test(expected = EntityNotFoundException.class)
    public void  shouldThrowEntityNotFoundException2() {
       categoryService.findById(0);
    }

}