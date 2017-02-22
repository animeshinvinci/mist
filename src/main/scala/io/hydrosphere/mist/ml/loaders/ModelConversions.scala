package io.hydrosphere.mist.ml.loaders

import io.hydrosphere.mist.ml.loaders.classification.{LocalDecisionTreeClassificationModel, LocalPerceptron, LocalRandomForestClassificationModel}
import io.hydrosphere.mist.ml.loaders.clustering.{LocalGaussianMixtureModel, LocalKMeans}
import io.hydrosphere.mist.ml.loaders.preprocessors._
import io.hydrosphere.mist.ml.loaders.regression.LocalLogisticRegressionModel
import org.apache.spark.ml.PipelineModel
import org.apache.spark.ml.classification._
import org.apache.spark.ml.clustering.KMeansModel
import org.apache.spark.ml.feature._
import org.apache.spark.ml.clustering.GaussianMixtureModel

import scala.language.implicitConversions

object ModelConversions {
  implicit def anyToLocal(m: Any): LocalModel = m match {
    case _: MultilayerPerceptronClassificationModel.type => LocalPerceptron
    case _: Tokenizer.type => LocalTokenizer
    case _: HashingTF.type  => LocalHashingTF
    case _: StringIndexerModel.type  => LocalStringIndexer
    case _: LogisticRegressionModel.type  => LocalLogisticRegressionModel
    case _: PipelineModel.type  => LocalPipelineModel
    case _: DecisionTreeClassificationModel.type  => LocalDecisionTreeClassificationModel
    case _: RandomForestClassificationModel.type => LocalRandomForestClassificationModel
    case _: KMeansModel.type => LocalKMeans
    case _: GaussianMixtureModel.type => LocalGaussianMixtureModel
    case _: Binarizer.type => LocalBinarizer
    case _: PCAModel.type => LocalPCA
    case _: StandardScalerModel.type => LocalStandardScaler
    case _: MaxAbsScaler.type => LocalMaxAbsScaler
    case _: MinMaxScalerModel.type => LocalMinMaxScaler
    case _ => throw new Exception(s"Unknown transformer: ${m.getClass}")
  }
}
