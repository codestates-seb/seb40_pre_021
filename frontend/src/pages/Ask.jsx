import styled from 'styled-components';
import { useState } from 'react';
import Editor from '../components/Question/Editor';

const Container = styled.section``;
const AskQuestion = styled.h1``;
const WritingTip = styled.div``;
const TitleGroup = styled.div``;
const Title = styled.h3``;
const TitleCaption = styled.span``;
const TitleTip = styled.div``;
const TitleForm = styled.input``;
const ProblemDetail = styled.div``;
const ProblemTitle = styled.h3``;
const ProblemCaption = styled.span``;
const ProblemTip = styled.div``;
const Next = styled.button``;
const Expecting = styled.div``;
const ExpectingTitle = styled.h3``;
const ExpectingCaption = styled.span``;
const ExpectingTip = styled.div``;
const TagsGroup = styled.div``;
const TagsTitle = styled.h3``;
const TagsCaption = styled.span``;
const TagsForm = styled.input``;
const QuestionSend = styled.button``;
// const DiscardDraft = styled.button``;

const Ask = () => {
	return (
		<Container>
			<AskQuestion />
			<WritingTip>
				<h2>Writing a good question</h2>
				<p>
					You’re ready to ask a programming-related question and this form will
					help guide you through the process. Looking to ask a non-programming
					question? See the topics here to find a relevant site.
				</p>
				<p>Steps</p>
				<ul>
					<li>Summarize your problem in a one-line title.</li>
					<li>Describe your problem in more detail.</li>
					<li>Describe what you tried and what you expected to happen.</li>
					<li>
						Add “tags” which help surface your question to members of the
						community.
					</li>
					<li>Review your question and post it to the site.</li>
				</ul>
			</WritingTip>
			<TitleGroup>
				<Title>
					<label htmlFor="title">Title</label>
				</Title>
				<TitleCaption>
					<label htmlFor="title">
						Be specific and imagine you’re asking a question to another person.
					</label>
				</TitleCaption>
				<TitleForm
					id="title"
					name="title"
					type="text"
					maxlength="300"
					placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
				/>
			</TitleGroup>
			<TitleTip></TitleTip>
			<ProblemDetail>
				<ProblemTitle>What are the details of your problem?</ProblemTitle>
				<ProblemCaption>
					Introduce the problem and expand on what you put in the title. Minimum
					20 characters.
				</ProblemCaption>
				<Editor />
				<Next />
			</ProblemDetail>
			<ProblemTip></ProblemTip>
			<Expecting>
				<ExpectingTitle>
					What did you try and what were you expecting?
				</ExpectingTitle>
				<ExpectingCaption>
					Describe what you tried, what you expected to happen, and what
					actually resulted. Minimum 20 characters.
				</ExpectingCaption>
				<Editor />
			</Expecting>
			<ExpectingTip></ExpectingTip>
			<TagsGroup>
				<TagsTitle>Tags</TagsTitle>
				<TagsCaption>
					Add up to 5 tags to describe what your question is about. Start typing
					to see suggestions.
				</TagsCaption>
				<TagsForm></TagsForm>
			</TagsGroup>
			<QuestionSend />
		</Container>
	);
};

export default Ask;
