package fr.jukien.intellij.plugins.ui;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created on 24/04/2019
 *
 * @author JDI
 * @version 2.3.0
 * @since 1.0.0
 */
public class POJOGeneratorConfigurable implements Configurable {
    private final POJOGeneratorSettings pojoGeneratorSettings;
    private POJOGeneratorPanel pojoGeneratorPanel;

    public POJOGeneratorConfigurable(Project project) {
        this.pojoGeneratorSettings = ServiceManager.getService(project, POJOGeneratorSettings.class);
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "POJO Generator";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        if (null == pojoGeneratorPanel) {
            pojoGeneratorPanel = new POJOGeneratorPanel(pojoGeneratorSettings);
        }

        updateEnabled();
        pojoGeneratorPanel.getGenerateCompositePrimaryKeyCheckBox().addItemListener(e -> updateEnabled());

        ButtonGroup group = new ButtonGroup();
        group.add(pojoGeneratorPanel.getIdClassAnnotationRadioButton());
        group.add(pojoGeneratorPanel.getEmbeddedIdAnnotationRadioButton());

        return pojoGeneratorPanel.getPanel();
    }

    @Override
    public boolean isModified() {
        if (pojoGeneratorPanel.getCapitalizeCheckBox().isSelected() != pojoGeneratorSettings.getCapitalize()) return true;
        if (pojoGeneratorPanel.getSchemaAttributeCheckBox().isSelected() != pojoGeneratorSettings.getWithSchemaAttribute()) return true;
        if (pojoGeneratorPanel.getGeneratedValueAnnotationsCheckBox().isSelected() != pojoGeneratorSettings.getAutoGenerated()) return true;
        if (pojoGeneratorPanel.getManyToOneAndJoinColumnAnnotationsCheckBox().isSelected() != pojoGeneratorSettings.getWithRelationshipAnnotations()) return true;
        if (pojoGeneratorPanel.getGenerateCompositePrimaryKeyCheckBox().isSelected() != pojoGeneratorSettings.getGenerateCompositePrimaryKey()) return true;
        if (pojoGeneratorPanel.getIdClassAnnotationRadioButton().isSelected() != pojoGeneratorSettings.getGenerateCompositePrimaryKeyWithIdClassAnnotation()) return true;
        if (pojoGeneratorPanel.getEmbeddedIdAnnotationRadioButton().isSelected() != pojoGeneratorSettings.getGenerateCompositePrimaryKeyWithEmbeddedIdAnnotation()) return true;
        if (!pojoGeneratorPanel.getPrefixCompositePrimaryKeyTextField().getText().equals(pojoGeneratorSettings.getPrefixCompositePrimaryKey())) return true;
        if (!pojoGeneratorPanel.getSuffixCompositePrimaryKeyTextField().getText().equals(pojoGeneratorSettings.getSuffixCompositePrimaryKey())) return true;
        if (!pojoGeneratorPanel.getPrefixEntityTextField().getText().equals(pojoGeneratorSettings.getPrefixEntity())) return true;
        if (!pojoGeneratorPanel.getSuffixEntityTextField().getText().equals(pojoGeneratorSettings.getSuffixEntity())) return true;
        if (!pojoGeneratorPanel.getPrefixDTOTextField().getText().equals(pojoGeneratorSettings.getPrefixDto())) return true;
        if (!pojoGeneratorPanel.getSuffixDTOTextField().getText().equals(pojoGeneratorSettings.getSuffixDto())) return true;
        if (pojoGeneratorPanel.getAlwaysShowDifferencesBetweenFilesCheckBox().isSelected() != pojoGeneratorSettings.getAlwaysShowDifferencesBetweenFiles()) return true;
        return false;
    }

    @Override
    public void apply() {
        pojoGeneratorSettings.setCapitalize(pojoGeneratorPanel.getCapitalizeCheckBox().isSelected());
        pojoGeneratorSettings.setWithSchemaAttribute(pojoGeneratorPanel.getSchemaAttributeCheckBox().isSelected());
        pojoGeneratorSettings.setAutoGenerated(pojoGeneratorPanel.getGeneratedValueAnnotationsCheckBox().isSelected());
        pojoGeneratorSettings.setWithRelationshipAnnotations(pojoGeneratorPanel.getManyToOneAndJoinColumnAnnotationsCheckBox().isSelected());
        pojoGeneratorSettings.setGenerateCompositePrimaryKey(pojoGeneratorPanel.getGenerateCompositePrimaryKeyCheckBox().isSelected());
        pojoGeneratorSettings.setGenerateCompositePrimaryKeyWithIdClassAnnotation(pojoGeneratorPanel.getIdClassAnnotationRadioButton().isSelected());
        pojoGeneratorSettings.setGenerateCompositePrimaryKeyWithEmbeddedIdAnnotation(pojoGeneratorPanel.getEmbeddedIdAnnotationRadioButton().isSelected());
        pojoGeneratorSettings.setPrefixCompositePrimaryKey(pojoGeneratorPanel.getPrefixCompositePrimaryKeyTextField().getText());
        pojoGeneratorSettings.setSuffixCompositePrimaryKey(pojoGeneratorPanel.getSuffixCompositePrimaryKeyTextField().getText());
        pojoGeneratorSettings.setPrefixEntity(pojoGeneratorPanel.getPrefixEntityTextField().getText());
        pojoGeneratorSettings.setSuffixEntity(pojoGeneratorPanel.getSuffixEntityTextField().getText());
        pojoGeneratorSettings.setPrefixDto(pojoGeneratorPanel.getPrefixDTOTextField().getText());
        pojoGeneratorSettings.setSuffixDto(pojoGeneratorPanel.getSuffixDTOTextField().getText());
        pojoGeneratorSettings.setAlwaysShowDifferencesBetweenFiles(pojoGeneratorPanel.getAlwaysShowDifferencesBetweenFilesCheckBox().isSelected());
    }

    private boolean isGenerateCompositePrimaryKeyEnabled() {
        return pojoGeneratorPanel.getGenerateCompositePrimaryKeyCheckBox() != null && pojoGeneratorPanel.getGenerateCompositePrimaryKeyCheckBox().isSelected();
    }

    private void updateEnabled() {
        boolean enabled = isGenerateCompositePrimaryKeyEnabled();
        if (pojoGeneratorPanel.getIdClassAnnotationRadioButton() != null) {
            pojoGeneratorPanel.getIdClassAnnotationRadioButton().setEnabled(enabled);
        }
        if (pojoGeneratorPanel.getEmbeddedIdAnnotationRadioButton() != null) {
            pojoGeneratorPanel.getEmbeddedIdAnnotationRadioButton().setEnabled(enabled);
        }
        if (pojoGeneratorPanel.getPrefixCompositePrimaryKeyLabel() != null) {
            pojoGeneratorPanel.getPrefixCompositePrimaryKeyLabel().setEnabled(enabled);
        }
        if (pojoGeneratorPanel.getPrefixCompositePrimaryKeyTextField() != null) {
            pojoGeneratorPanel.getPrefixCompositePrimaryKeyTextField().setEnabled(enabled);
        }
        if (pojoGeneratorPanel.getSuffixCompositePrimaryKeyLabel() != null) {
            pojoGeneratorPanel.getSuffixCompositePrimaryKeyLabel().setEnabled(enabled);
        }
        if (pojoGeneratorPanel.getSuffixCompositePrimaryKeyTextField() != null) {
            pojoGeneratorPanel.getSuffixCompositePrimaryKeyTextField().setEnabled(enabled);
        }
    }
}
