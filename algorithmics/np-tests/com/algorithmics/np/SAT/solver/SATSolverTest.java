package com.algorithmics.np.SAT.solver;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;

import com.algorithmics.minisat.MiniSatSystemCallSATSolver;
import com.algorithmics.np.SAT.instance.Variable;
import com.algorithmics.np.SAT.instance.VariableAssignment;
import com.algorithmics.np.SAT.instance.CNF.Clause;
import com.algorithmics.np.SAT.instance.CNF.Literal;
import com.algorithmics.np.SAT.instance.CNF.SentenceInCNF;
import com.algorithmics.np.SAT.instance.tree.SentenceTree;
import com.algorithmics.np.SAT.preprocessor.DimacsReader;
import com.algorithmics.np.SAT.preprocessor.SATParser;
import com.algorithmics.np.SAT.solver.SATSolverRecursive;

public class SATSolverTest {

	@Test
	public void emptyTrueAndFalseSentence() {

		SATSolverRecursive solver = new SATSolverRecursive();

		Optional<VariableAssignment> result1 = solver.solve(SentenceInCNF.constructMinimalTrueSentence());
		assertTrue(result1.isPresent());
		Optional<VariableAssignment> result2 = solver.solve(SentenceInCNF.constructMinimalFalseSentence());
		assertTrue(!result2.isPresent());

	}

	@Test
	public void singleVar() {
		SATSolverRecursive solver = new SATSolverRecursive();

		Variable v1 = new Variable("X");

		Literal l1 = new Literal(v1);
		ArrayList<Literal> literals1 = new ArrayList<Literal>();
		literals1.add(l1);
		Clause c1 = new Clause(literals1);
		ArrayList<Clause> clauses = new ArrayList<Clause>();
		clauses.add(c1);

		SentenceInCNF p = new SentenceInCNF(clauses);
		Optional<VariableAssignment> variableAssignment;
		try {
			variableAssignment = solver.solve(p);
			assertTrue(variableAssignment.isPresent());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void singleVarNegated() {
		SATSolverRecursive solver = new SATSolverRecursive();

		Variable v1 = new Variable("X");
		Literal l1 = new Literal(v1);
		l1.setNegated(true);
		ArrayList<Literal> literals1 = new ArrayList<Literal>();
		literals1.add(l1);
		Clause c1 = new Clause(literals1);
		ArrayList<Clause> clauses = new ArrayList<Clause>();
		clauses.add(c1);

		SentenceInCNF p = new SentenceInCNF(clauses);
		Optional<VariableAssignment> variableAssignment;
		variableAssignment = solver.solve(p);
		assertTrue(variableAssignment.isPresent());

	}

	@Test
	public void singleVarNegatedXAndNotX() {
		SATSolverRecursive solver = new SATSolverRecursive();

		Variable varaiableX = new Variable("X");
		Literal literal1 = new Literal(varaiableX);
		literal1.setNegated(true);

		Literal literal2 = new Literal(varaiableX);
		literal2.setNegated(false);

		ArrayList<Literal> literals1 = new ArrayList<Literal>();
		literals1.add(literal1);

		ArrayList<Literal> literals2 = new ArrayList<Literal>();
		literals2.add(literal2);

		Clause clause1 = new Clause(literals1);
		Clause clause2 = new Clause(literals2);
		ArrayList<Clause> clauses = new ArrayList<Clause>();
		clauses.add(clause1);
		clauses.add(clause2);

		SentenceInCNF p = new SentenceInCNF(clauses);
		Optional<VariableAssignment> variableAssignment;

		variableAssignment = solver.solve(p);
		assertTrue(!variableAssignment.isPresent());
	}

	@Test
	public void stringtest() {

		SATSolverRecursive solver = new SATSolverRecursive();
		String satSentence = "(x OR NOT y OR z)AND(NOT x)AND(NOT w OR NOT x OR y OR z) ";

		Optional<VariableAssignment> solution = solver.solve(satSentence);

		assertTrue(solution.isPresent());
	}

	@Test
	public void testWithNumber() {

		SATSolverRecursive solver = new SATSolverRecursive();
		String satSentence = "(0 + 1 + 2) & (-1 + -3)";

		Optional<VariableAssignment> solution = solver.solve(satSentence);
		assertTrue(solution.isPresent());
	}

	@Test
	public void testAlotOfSatisfiableInstance() {
		SATSolverRecursive solver = new SATSolverRecursive();
		try {
			Files.list(Paths.get("data/uf20-91")).forEach(p -> {
				String satSentenceFilePath = p.toString();
				DimacsReader dimacsReader = new DimacsReader();
				
				SentenceInCNF sentence = dimacsReader.readFromFile(satSentenceFilePath);
				Optional<VariableAssignment> solution = solver.solve(sentence);
				assertTrue(solution.isPresent());
			});

		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testRemovePureLiterals() {
		String sat = "(A OR B) AND  (NOT B)";
		SATParser parser = new SATParser();
		SentenceTree sentence = parser.parse(sat);
		SentenceInCNF cnf = sentence.toCNF();
		cnf.removePureLiterals();
	}

	@Test
	public void testAlotOfUnsatisfiableInstance() {

		MiniSatSystemCallSATSolver solver = new MiniSatSystemCallSATSolver();
		try {
			Files.list(Paths.get("data/uuf50-218")).forEach(p -> {
				String satSentenceFilePath = p.toString();
				DimacsReader dr = new DimacsReader();
				SentenceInCNF sentence = dr.readFromFile(satSentenceFilePath);
				Optional<VariableAssignment> solution = solver.solve(sentence.toDimacsFile());
				assertFalse(solution.isPresent());
			});

		} catch (IOException e) {
			fail(e.getMessage());
		}

	}

}
