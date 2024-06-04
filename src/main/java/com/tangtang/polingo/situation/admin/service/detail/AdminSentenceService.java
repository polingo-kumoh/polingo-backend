package com.tangtang.polingo.situation.admin.service.detail;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.situation.admin.dto.detail.SentenceUpdateRequest;
import com.tangtang.polingo.situation.entity.SituationSentence;
import com.tangtang.polingo.situation.repository.SentenceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminSentenceService {
    private final SentenceRepository sentenceRepository;
    // 문장 수정
    public void updateSentence(Long sentenceId, SentenceUpdateRequest request) {
        SituationSentence sentence = sentenceRepository.findById(sentenceId)
                .orElseThrow(() -> new EntityNotFoundException("Sentence not found"));
        sentence.update(request.getOriginText(), request.getTranslatedText(), Language.valueOf(request.getLanguage()));
        sentenceRepository.save(sentence);
    }

    // 문장 삭제
    public void deleteSentence(Long sentenceId) {
        sentenceRepository.deleteById(sentenceId);
    }
}
