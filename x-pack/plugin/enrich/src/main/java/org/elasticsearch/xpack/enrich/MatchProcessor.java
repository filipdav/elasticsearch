/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.xpack.enrich;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;

import java.util.function.BiConsumer;

public class MatchProcessor extends AbstractEnrichProcessor {

    MatchProcessor(String tag,
                   Client client,
                   String policyName,
                   String field,
                   String targetField,
                   boolean overrideEnabled,
                   boolean ignoreMissing,
                   String matchField,
                   int maxMatches) {
        super(tag, client, policyName, field, targetField, ignoreMissing, overrideEnabled, matchField, maxMatches);
    }

    /** used in tests **/
    MatchProcessor(String tag,
                   BiConsumer<SearchRequest, BiConsumer<SearchResponse, Exception>> searchRunner,
                   String policyName,
                   String field,
                   String targetField,
                   boolean overrideEnabled,
                   boolean ignoreMissing,
                   String matchField,
                   int maxMatches) {
        super(tag, searchRunner, policyName, field, targetField, ignoreMissing, overrideEnabled, matchField, maxMatches);
    }

    @Override
    public QueryBuilder getQueryBuilder(Object fieldValue) {
        return new TermQueryBuilder(matchField, fieldValue);
    }
}
