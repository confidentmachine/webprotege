package edu.stanford.bmir.protege.web.server.form;

import edu.stanford.bmir.protege.web.server.access.AccessManager;
import edu.stanford.bmir.protege.web.server.dispatch.AbstractHasProjectActionHandler;
import edu.stanford.bmir.protege.web.server.dispatch.ExecutionContext;
import edu.stanford.bmir.protege.web.shared.form.CollectionId;
import edu.stanford.bmir.protege.web.shared.form.SetFormDataAction;
import edu.stanford.bmir.protege.web.shared.form.SetFormDataResult;
import edu.stanford.bmir.protege.web.shared.project.ProjectId;

import javax.annotation.Nonnull;
import javax.inject.Inject;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 25 Jun 2017
 */
public class SetFormDataActionHandler extends AbstractHasProjectActionHandler<SetFormDataAction, SetFormDataResult> {

    @Nonnull
    private final FormDataRepository repository;

    @Nonnull
    private final ProjectId projectId;

    @Inject
    public SetFormDataActionHandler(@Nonnull AccessManager accessManager,
                                    @Nonnull FormDataRepository repository,
                                    @Nonnull ProjectId projectId) {
        super(accessManager);
        this.repository = repository;
        this.projectId = projectId;
    }

    @Override
    public Class<SetFormDataAction> getActionClass() {
        return SetFormDataAction.class;
    }

    @Override
    public SetFormDataResult execute(SetFormDataAction action, ExecutionContext executionContext) {
        repository.store(projectId, CollectionId.get("12345678-1234-1234-1234-123456789abc"), action.getFormId(), action.getEntity(), action.getFormData());
        return new SetFormDataResult();
    }
}