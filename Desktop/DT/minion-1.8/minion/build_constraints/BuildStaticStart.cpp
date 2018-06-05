#include "BuildStart.h"
AbstractConstraint* build_constraint(StateObj* stateObj, ConstraintBlob& b) {
switch(b.constraint->type) {
case CT_ELEMENT: return build_constraint_CT_ELEMENT(stateObj, b);
case CT_ELEMENT_UNDEFZERO: return build_constraint_CT_ELEMENT_UNDEFZERO(stateObj, b);
case CT_ELEMENT_ONE: return build_constraint_CT_ELEMENT_ONE(stateObj, b);
case CT_WATCHED_ELEMENT: return build_constraint_CT_WATCHED_ELEMENT(stateObj, b);
case CT_WATCHED_ELEMENT_ONE: return build_constraint_CT_WATCHED_ELEMENT_ONE(stateObj, b);
case CT_WATCHED_ELEMENT_UNDEFZERO: return build_constraint_CT_WATCHED_ELEMENT_UNDEFZERO(stateObj, b);
case CT_GACELEMENT: return build_constraint_CT_GACELEMENT(stateObj, b);
case CT_ALLDIFF: return build_constraint_CT_ALLDIFF(stateObj, b);
case CT_GACALLDIFF: return build_constraint_CT_GACALLDIFF(stateObj, b);
case CT_GCC: return build_constraint_CT_GCC(stateObj, b);
case CT_GCCWEAK: return build_constraint_CT_GCCWEAK(stateObj, b);
case CT_ALLDIFFMATRIX: return build_constraint_CT_ALLDIFFMATRIX(stateObj, b);
case CT_WATCHED_NEQ: return build_constraint_CT_WATCHED_NEQ(stateObj, b);
case CT_DISEQ: return build_constraint_CT_DISEQ(stateObj, b);
case CT_DISEQ_REIFY: return build_constraint_CT_DISEQ_REIFY(stateObj, b);
case CT_EQ: return build_constraint_CT_EQ(stateObj, b);
case CT_EQ_REIFY: return build_constraint_CT_EQ_REIFY(stateObj, b);
case CT_MINUSEQ: return build_constraint_CT_MINUSEQ(stateObj, b);
case CT_MINUSEQ_REIFY: return build_constraint_CT_MINUSEQ_REIFY(stateObj, b);
case CT_ABS: return build_constraint_CT_ABS(stateObj, b);
case CT_INEQ: return build_constraint_CT_INEQ(stateObj, b);
case CT_WATCHED_LESS: return build_constraint_CT_WATCHED_LESS(stateObj, b);
case CT_GACLEXLEQ: return build_constraint_CT_GACLEXLEQ(stateObj, b);
case CT_QUICK_LEXLEQ: return build_constraint_CT_QUICK_LEXLEQ(stateObj, b);
case CT_LEXLEQ: return build_constraint_CT_LEXLEQ(stateObj, b);
case CT_LEXLESS: return build_constraint_CT_LEXLESS(stateObj, b);
case CT_QUICK_LEXLESS: return build_constraint_CT_QUICK_LEXLESS(stateObj, b);
case CT_MAX: return build_constraint_CT_MAX(stateObj, b);
case CT_MIN: return build_constraint_CT_MIN(stateObj, b);
case CT_OCCURRENCE: return build_constraint_CT_OCCURRENCE(stateObj, b);
case CT_LEQ_OCCURRENCE: return build_constraint_CT_LEQ_OCCURRENCE(stateObj, b);
case CT_GEQ_OCCURRENCE: return build_constraint_CT_GEQ_OCCURRENCE(stateObj, b);
case CT_PRODUCT2: return build_constraint_CT_PRODUCT2(stateObj, b);
case CT_DIFFERENCE: return build_constraint_CT_DIFFERENCE(stateObj, b);
case CT_WEIGHTLEQSUM: return build_constraint_CT_WEIGHTLEQSUM(stateObj, b);
case CT_WEIGHTGEQSUM: return build_constraint_CT_WEIGHTGEQSUM(stateObj, b);
case CT_GEQSUM: return build_constraint_CT_GEQSUM(stateObj, b);
case CT_LEQSUM: return build_constraint_CT_LEQSUM(stateObj, b);
case CT_WATCHED_GEQSUM: return build_constraint_CT_WATCHED_GEQSUM(stateObj, b);
case CT_WATCHED_LEQSUM: return build_constraint_CT_WATCHED_LEQSUM(stateObj, b);
case CT_WATCHED_TABLE: return build_constraint_CT_WATCHED_TABLE(stateObj, b);
case CT_WATCHED_NEGATIVE_TABLE: return build_constraint_CT_WATCHED_NEGATIVE_TABLE(stateObj, b);
case CT_WATCHED_VECNEQ: return build_constraint_CT_WATCHED_VECNEQ(stateObj, b);
case CT_STATIC_VECNEQ: return build_constraint_CT_STATIC_VECNEQ(stateObj, b);
case CT_WATCHED_LITSUM: return build_constraint_CT_WATCHED_LITSUM(stateObj, b);
case CT_POW: return build_constraint_CT_POW(stateObj, b);
case CT_DIV: return build_constraint_CT_DIV(stateObj, b);
case CT_DIV_UNDEFZERO: return build_constraint_CT_DIV_UNDEFZERO(stateObj, b);
case CT_MODULO: return build_constraint_CT_MODULO(stateObj, b);
case CT_MODULO_UNDEFZERO: return build_constraint_CT_MODULO_UNDEFZERO(stateObj, b);
case CT_GADGET: return build_constraint_CT_GADGET(stateObj, b);
case CT_WATCHED_OR: return build_constraint_CT_WATCHED_OR(stateObj, b);
case CT_WATCHED_HAMMING: return build_constraint_CT_WATCHED_HAMMING(stateObj, b);
case CT_WATCHED_NOT_HAMMING: return build_constraint_CT_WATCHED_NOT_HAMMING(stateObj, b);
case CT_WATCHED_NEW_OR: return build_constraint_CT_WATCHED_NEW_OR(stateObj, b);
case CT_WATCHED_NEW_AND: return build_constraint_CT_WATCHED_NEW_AND(stateObj, b);
case CT_WATCHED_INSET: return build_constraint_CT_WATCHED_INSET(stateObj, b);
case CT_WATCHED_ININTERVALSET: return build_constraint_CT_WATCHED_ININTERVALSET(stateObj, b);
case CT_WATCHED_NOT_INSET: return build_constraint_CT_WATCHED_NOT_INSET(stateObj, b);
case CT_WATCHED_INRANGE: return build_constraint_CT_WATCHED_INRANGE(stateObj, b);
case CT_WATCHED_NOT_INRANGE: return build_constraint_CT_WATCHED_NOT_INRANGE(stateObj, b);
case CT_WATCHED_LIT: return build_constraint_CT_WATCHED_LIT(stateObj, b);
case CT_WATCHED_NOTLIT: return build_constraint_CT_WATCHED_NOTLIT(stateObj, b);
case CT_REIFY: return build_constraint_CT_REIFY(stateObj, b);
case CT_REIFYIMPLY_QUICK: return build_constraint_CT_REIFYIMPLY_QUICK(stateObj, b);
case CT_REIFYIMPLY: return build_constraint_CT_REIFYIMPLY(stateObj, b);
case CT_TRUE: return build_constraint_CT_TRUE(stateObj, b);
case CT_FALSE: return build_constraint_CT_FALSE(stateObj, b);
case CT_CHECK_GSA: return build_constraint_CT_CHECK_GSA(stateObj, b);
case CT_CHECK_ASSIGN: return build_constraint_CT_CHECK_ASSIGN(stateObj, b);
case CT_FORWARD_CHECKING: return build_constraint_CT_FORWARD_CHECKING(stateObj, b);
case CT_WATCHED_VEC_OR_LESS: return build_constraint_CT_WATCHED_VEC_OR_LESS(stateObj, b);
case CT_LIGHTTABLE: return build_constraint_CT_LIGHTTABLE(stateObj, b);
case CT_HAGGISGAC: return build_constraint_CT_HAGGISGAC(stateObj, b);
case CT_HAGGISGAC_STABLE: return build_constraint_CT_HAGGISGAC_STABLE(stateObj, b);
case CT_STR: return build_constraint_CT_STR(stateObj, b);
case CT_SHORTSTR: return build_constraint_CT_SHORTSTR(stateObj, b);
case CT_GACEQ: return build_constraint_CT_GACEQ(stateObj, b);
case CT_GACSCHEMA: return build_constraint_CT_GACSCHEMA(stateObj, b);
case CT_MDDC: return build_constraint_CT_MDDC(stateObj, b);
case CT_NEGATIVEMDDC: return build_constraint_CT_NEGATIVEMDDC(stateObj, b);
case CT_TEST: return build_constraint_CT_TEST(stateObj, b);
case CT_VM: return build_constraint_CT_VM(stateObj, b);
case CT_VM_SYM: return build_constraint_CT_VM_SYM(stateObj, b);
case CT_TABLE_VM: return build_constraint_CT_TABLE_VM(stateObj, b);
case CT_NEG_TABLE_VM: return build_constraint_CT_NEG_TABLE_VM(stateObj, b);
default: D_FATAL_ERROR("Fatal error building constraints");
}}