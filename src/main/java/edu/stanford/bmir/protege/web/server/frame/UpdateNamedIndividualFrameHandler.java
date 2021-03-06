package edu.stanford.bmir.protege.web.server.frame;

import edu.stanford.bmir.protege.web.client.dispatch.actions.UpdateNamedIndividualFrameAction;
import edu.stanford.bmir.protege.web.client.frame.LabelledFrame;
import edu.stanford.bmir.protege.web.server.access.AccessManager;
import edu.stanford.bmir.protege.web.server.change.HasApplyChanges;
import edu.stanford.bmir.protege.web.server.change.ReverseEngineeredChangeDescriptionGeneratorFactory;
import edu.stanford.bmir.protege.web.server.events.EventManager;
import edu.stanford.bmir.protege.web.shared.dispatch.Result;
import edu.stanford.bmir.protege.web.shared.dispatch.UpdateObjectResult;
import edu.stanford.bmir.protege.web.shared.entity.OWLNamedIndividualData;
import edu.stanford.bmir.protege.web.shared.event.ProjectEvent;
import edu.stanford.bmir.protege.web.shared.events.EventList;
import edu.stanford.bmir.protege.web.shared.frame.NamedIndividualFrame;
import org.semanticweb.owlapi.model.OWLOntology;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 20/02/2013
 */
public class UpdateNamedIndividualFrameHandler extends AbstractUpdateFrameHandler<UpdateNamedIndividualFrameAction, NamedIndividualFrame, OWLNamedIndividualData> {

    @Nonnull
    private final Provider<NamedIndividualFrameTranslator> translatorProvider;

    @Inject
    public UpdateNamedIndividualFrameHandler(@Nonnull AccessManager accessManager,
                                             @Nonnull ReverseEngineeredChangeDescriptionGeneratorFactory changeDescriptionGeneratorFactory,
                                             @Nonnull EventManager<ProjectEvent<?>> eventManager,
                                             @Nonnull HasApplyChanges applyChanges,
                                             @Nonnull OWLOntology rootOntology,
                                             @Nonnull Provider<NamedIndividualFrameTranslator> translatorProvider) {
        super(accessManager, changeDescriptionGeneratorFactory, eventManager, applyChanges, rootOntology);
        this.translatorProvider = translatorProvider;
    }

    /**
     * Gets the class of {@link edu.stanford.bmir.protege.web.shared.dispatch.Action} handled by this handler.
     * @return The class of {@link edu.stanford.bmir.protege.web.shared.dispatch.Action}.  Not {@code null}.
     */
    @Override
    public Class<UpdateNamedIndividualFrameAction> getActionClass() {
        return UpdateNamedIndividualFrameAction.class;
    }

    @Override
    protected Result createResponse(LabelledFrame<NamedIndividualFrame> to, EventList<ProjectEvent<?>> events) {
        return new UpdateObjectResult(events);
    }

    @Override
    protected FrameTranslator<NamedIndividualFrame, OWLNamedIndividualData> createTranslator() {
        return translatorProvider.get();
    }

    @Override
    protected String getChangeDescription(LabelledFrame<NamedIndividualFrame> from, LabelledFrame<NamedIndividualFrame> to) {
        return "Edited individual";
    }
}
