import styled from 'styled-components';
import { useState } from 'react';
import Editor from '../components/common/Editor';

const QuestionContainer = styled.section``;
const QuestionGroup = styled.article``;
const Header = styled.div``;
const Title = styled.h1``;
const AskQuestion = styled.button``;
const Info = styled.span``;
const QController = styled.div``;
const Body = styled.div``;
const Tags = styled.div``;
const Options = styled.div``;
const Profile = styled.div``;
const Comments = styled.div``;
const AnswerGroup = styled.article``;
const AnswerCount = styled.h2``;
const AController = styled.div``;
const EditGroup = styled.div``;
const YourAnswer = styled.h1``;
const PostYourAnswer = styled.button``;

const QuestionEx = {
	question: [
		{
			questionId: 'string',
			title: 'string',
			body: 'string',
			tags: 'string',
			votes: 'int',
			answers: 'int',
			views: 'int',
			nickname: 'string',
			createdAt: 'string',
			comments: [
				{
					commentId: 'string',
					commentBody: 'string2',
					createdAt: 'string',
					nickname: 'string',
				},
				{
					commentId: 'string',
					commentBody: 'string2',
					createdAt: 'string',
					nickname: 'string',
				},
			],
		},
	],
};
const AnswerEx = {
	answer: [
		{
			answers: [
				{
					answerId: 'string',
					body: 'string',
					votes: 'int',
					bookmarked: 'bool',
					choosed: 'bool',
					createdAt: 'string',
					modifiedAt: 'string',
					comments: [
						{
							commentId: 'string',
							commentBody: 'string',
							createdAt: 'string',
							nickname: 'string',
						},
						{
							commentId: 'string1',
							commentBody: 'string2',
							createdAt: 'string3',
							nickname: 'string4',
						},
					],
				},
				{
					answerId: 'string2',
					body: 'string4',
					votes: 'int',
					bookmarked: 'bool',
					choosed: 'bool',
					createdAt: 'string',
					modifiedAt: 'string',
					comments: [
						{
							commentId: 'string',
							commentBody: 'string',
							createdAt: 'string',
							nickname: 'string',
						},
						{
							commentId: 'string1',
							commentBody: 'string2',
							createdAt: 'string3',
							nickname: 'string4',
						},
					],
				},
			],
		},
	],
};

const Question = () => {
	return (
		<>
			<QuestionContainer>
				<QuestionGroup>
					<Header>
						<Title>{QuestionEx.question[0].title}</Title>
						<Info>Asked {QuestionEx.question[0].createdAt}</Info>
						<AskQuestion />
					</Header>
					<QController />
					<Body>{QuestionEx.question[0].body}</Body>
					<Tags>{QuestionEx.question[0].tags}</Tags>
					<Options>
						<span>Share</span>
						<span>Edit</span>
					</Options>
					<Profile></Profile>
					{QuestionEx.question[0].comments.map((c) => (
						<Comments key={c.commentId}>{c.commentBody}</Comments>
					))}
				</QuestionGroup>
				<AnswerGroup>
					<AnswerCount>{AnswerEx.answer[0].answers.length} Answers</AnswerCount>
					{AnswerEx.answer[0].answers.map((el) => (
						<>
							<AController />
							<Body>{el.body}</Body>
							<Options>
								<span>Share</span>
								<span>Edit</span>
							</Options>
							<Profile></Profile>
							{el.comments.map((c) => (
								<Comments key={c.commentId}>{c.commentBody}</Comments>
							))}
						</>
					))}
				</AnswerGroup>
				<EditGroup>
					<YourAnswer />
					<Editor />
					<PostYourAnswer />
				</EditGroup>
			</QuestionContainer>
		</>
	);
};

export default Question;
