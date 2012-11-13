package jmeter.examples;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class JavaRequestSampler extends AbstractJavaSamplerClient implements
		Serializable {
	// TODO: call rest WS
	// TODO: use basic authentication: e.g. localhost:8080/manager/html (acct:
	// ??)
	// TODO: call async WS

	// TODO: sdb restapi traversal. DU, parent, child, no. of levels etc
	// maybe can use xip api to parse returned response also

	// TODO: to take rest api ws url and callback url from jmeter
	// and pass these to AuthenticationTest (for ingest)

	// TODO: for access, just use jersey straight
	// write a code to work out parent(level), siblings, children(level)
	private static final String LEVEL_PARAM_NAME = "Level";
	private static final String DU_PARAM_NAME = "DU";
	private static final String PARENTS_PARAM_NAME = "Parents";
	private String level;
	private String du;
	private String parents;

	public JavaRequestSampler() {
		super();
	}

	public Arguments getDefaultParameters() {
		Arguments params = new Arguments();
		params.addArgument(LEVEL_PARAM_NAME, "${level}");
		params.addArgument(DU_PARAM_NAME, "${du}");
		params.addArgument(PARENTS_PARAM_NAME, "${parents}");
		return params;
	}

	// @Override
	public SampleResult runTest(JavaSamplerContext arg0) {
		JMeterVariables vars = JMeterContextService.getContext().getVariables();
		vars.put("demo", "demoVariableContent");

		setupValues(arg0);
		SampleResult sampleResult = new SampleResult();
		sampleResult.sampleStart();

		System.out.println("/level/du/parents: " + level + ", " + du + ", " + parents);
		//JUnitCore junit = new JUnitCore();
		//Result result = junit.run(AuthenticationTest.class);

//		if (result.getFailureCount() > 0) {
//			sampleResult.setSuccessful(false);
//			sampleResult.setResponseCode("301");
//			sampleResult.setResponseMessage("FailureCount: "
//					+ result.getFailureCount());
//		} else {
			sampleResult.setSuccessful(true);
			sampleResult.setResponseCodeOK();
			sampleResult.setResponseMessageOK();
//		}
		sampleResult.sampleEnd();
		return sampleResult;
	}

	private void setupValues(JavaSamplerContext context) {
		// TODO Auto-generated method stub
		level = context.getParameter(LEVEL_PARAM_NAME);
		du = context.getParameter(DU_PARAM_NAME);
		parents = context.getParameter(PARENTS_PARAM_NAME);
		
	}

	public void setupTest(JavaSamplerContext context) {
		getLogger().debug(whoAmI() + "\tsetupTest()");
		listParameters(context);
	}

	/**
	 * Do any clean-up required by this test. In this case no clean-up is
	 * necessary, but some messages are logged for debugging purposes.
	 * 
	 * @param context
	 *            the context to run with. This provides access to
	 *            initialization parameters.
	 */
	public void teardownTest(JavaSamplerContext context) {
		getLogger().debug(whoAmI() + "\tteardownTest()");
		listParameters(context);
	}

	/**
	 * Dump a list of the parameters in this context to the debug log.
	 * 
	 * @param context
	 *            the context which contains the initialization parameters.
	 */
	private void listParameters(JavaSamplerContext context) {
		if (getLogger().isDebugEnabled()) {
			Iterator argsIt = context.getParameterNamesIterator();
			while (argsIt.hasNext()) {
				String name = (String) argsIt.next();
				getLogger().debug(name + "=" + context.getParameter(name));
			}
		}
	}

	/**
	 * Generate a String identifier of this test for debugging purposes.
	 * 
	 * @return a String identifier for this test instance
	 */
	private String whoAmI() {
		StringBuffer sb = new StringBuffer();
		sb.append(Thread.currentThread().toString());
		sb.append("@");
		sb.append(Integer.toHexString(hashCode()));
		return sb.toString();
	}

	// public static void main(String[] args) {
	// System.out.println("starting test...");
	// JUnitCore junit = new JUnitCore();
	// Result result = junit.run(AuthenticationTest.class);
	// System.out.println("result failure count: " + result.getFailureCount());
	// System.out.println("result run time: " + result.getRunTime());
	// System.out.println("ending test...");
	// }

}
