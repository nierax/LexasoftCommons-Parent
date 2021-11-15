module de.lexasoft.common {
	requires java.desktop;
	requires java.logging;

	exports de.lexasoft.common.model;
	exports de.lexasoft.util;
	exports de.lexasoft.common.math;

	opens de.lexasoft.common.cli;
	opens de.lexasoft.common.math;
	opens de.lexasoft.common.model;
	opens de.lexasoft.common.swing;
	opens de.lexasoft.util;
}