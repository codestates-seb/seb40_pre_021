import styled from 'styled-components';
import { useState } from 'react';
import Editor from '../components/Question/Editor';

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

const Question = () => {
	return (
		<>
			<QuestionContainer>
				<QuestionGroup>
					<Header>
						<Title />
						<Info />
						<AskQuestion />
					</Header>
					<QController />
					<Body />
					<Tags />
					<Options />
					<Profile />
					<Comments />
				</QuestionGroup>
				<AnswerGroup>
					<AnswerCount />
					<AController />
					<Body />
					<Options />
					<Profile />
					<Comments />
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
