package de.hpi.isg.dataprep.implementation;

import de.hpi.isg.dataprep.Consequences;
import de.hpi.isg.dataprep.model.error.PreparationError;
import de.hpi.isg.dataprep.model.target.preparator.Preparator;
import de.hpi.isg.dataprep.model.target.preparator.PreparatorImpl;
import de.hpi.isg.dataprep.preparators.ReplaceSubstring;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.util.CollectionAccumulator;

/**
 * @author Lan Jiang
 * @since 2018/8/29
 */
abstract public class ReplaceSubstringImpl extends PreparatorImpl {

    abstract protected Consequences executeLogic(ReplaceSubstring preparator,
                                                 Dataset<Row> dataFrame,
                                                 CollectionAccumulator<PreparationError> errorAccumulator);

    @Override
    protected final Consequences executePreparator(Preparator preparator, Dataset<Row> dataFrame) throws Exception {
        ReplaceSubstring preparator_ = this.getPreparatorInstance(preparator, ReplaceSubstring.class);
        CollectionAccumulator<PreparationError> errorAccumulator = this.createErrorAccumulator(preparator_, dataFrame);
        return this.executeLogic(preparator_, dataFrame, errorAccumulator);
    }
}