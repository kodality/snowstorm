package org.snomed.snowstorm.fhir.services;

import org.hl7.fhir.r4.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.snomed.snowstorm.TestConfig;
import org.snomed.snowstorm.core.data.domain.Concepts;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestConfig.class)
public class ConceptMapProviderTest extends AbstractFHIRTest {
	
	@Test
	public void testHistoricAssociation() throws FHIROperationException {
		//We're using MAIN so pass the unversioned version
		String vs = "http://snomed.info/sct/900000000000207008/version/UNVERSIONED?fhir_cm=" + Concepts.REFSET_SAME_AS_ASSOCIATION;
		String sourceTarget = "source=http://snomed.info/sct?fhir_vs&target=http://snomed.info/sct?fhir_vs";
		String url = "http://localhost:" + port + "/fhir/ConceptMap/$translate?code=" + sampleSCTID + "&system=http://snomed.info/sct&" + sourceTarget + "&url=" + vs;
		Parameters parameters = get(url);
		assertNotNull(parameters);
		Type t = parameters.getParameter("result");
		assertTrue(t.castToBoolean(t).booleanValue());
	}
	
}
