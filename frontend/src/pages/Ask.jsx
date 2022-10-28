import styled from 'styled-components';
import { useState } from 'react';
import Editor from '../components/Question/Editor';

const Container = styled.section``;
const AskQuestion = styled.h1``;
const WritingGood = styled.div``;
const TitleGroup = styled.div``;
const Title = styled.h3``;
const TitleTip = styled.span``;
const TitleGood = styled.div``;
const TitleForm = styled.input``;
const ProblemDetail = styled.div``;
const ProblemTitle = styled.h3``;
const ProblemTip = styled.span``;
const ProblemGood = styled.div``;
const Next = styled.button``;
const Expecting = styled.div``;
const ExpectingTitle = styled.h3``;
const ExpectingTip = styled.span``;
const ExpectingGood = styled.div``;
const TagsGroup = styled.div``;
const TagsTitle = styled.h3``;
const TagsTip = styled.span``;
const TagsForm = styled.input``;
const QuestionSend = styled.button``;
// const DiscardDraft = styled.button``;

const Ask = () => {
	return (
		<Container>
			<AskQuestion />
			<WritingGood></WritingGood>
			<TitleGroup>
				<Title></Title>
				<TitleTip></TitleTip>
				<TitleForm />
			</TitleGroup>
			<TitleGood></TitleGood>
			<ProblemDetail>
				<ProblemTitle></ProblemTitle>
				<ProblemTip></ProblemTip>
				<Editor />
				<Next />
			</ProblemDetail>
			<ProblemGood></ProblemGood>
			<Expecting>
				<ExpectingTitle></ExpectingTitle>
				<ExpectingTip></ExpectingTip>
				<Editor />
			</Expecting>
			<ExpectingGood></ExpectingGood>
			<TagsGroup>
				<TagsTitle></TagsTitle>
				<TagsTip></TagsTip>
				<TagsForm></TagsForm>
			</TagsGroup>
			<QuestionSend />
		</Container>
	);
};

export default Ask;
