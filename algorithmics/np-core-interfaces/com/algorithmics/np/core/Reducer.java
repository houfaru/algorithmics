package com.algorithmics.np.core;

public interface Reducer<NP_CERFICATE extends Certificate, PROBLEM extends NPProblem<NP_CERFICATE>, NP_HARD_CERTIFICATE extends Certificate, NP_HARD_PROBLEM extends NPHardProblem<NP_HARD_CERTIFICATE>> {

    NP_HARD_PROBLEM reduce(PROBLEM p);

    NP_CERFICATE interpretCertificate(NP_HARD_CERTIFICATE npHardCertificate);

}
